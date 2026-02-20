(() => {
    'use strict';

    const SVG_SIZE = 400;
    const CENTER = SVG_SIZE / 2;
    const MAX_R = 4;
    const SCALE = (CENTER - 40) / MAX_R;

    let svg, areaGroup, axesGroup, pointsGroup, tempPointsGroup;
    let currentR = 2;
    let storedPoints = [];

    function toSvgX(x) {
        return CENTER + x * SCALE;
    }

    function toSvgY(y) {
        return CENTER - y * SCALE;
    }

    function drawAxes() {
        if (!axesGroup) return;
        axesGroup.innerHTML = '';
        
        const xAxis = document.createElementNS('http://www.w3.org/2000/svg', 'line');
        xAxis.setAttribute('x1', '0');
        xAxis.setAttribute('y1', CENTER);
        xAxis.setAttribute('x2', SVG_SIZE);
        xAxis.setAttribute('y2', CENTER);
        xAxis.setAttribute('stroke', '#3c2b1a');
        xAxis.setAttribute('stroke-width', '2');
        axesGroup.appendChild(xAxis);

        const yAxis = document.createElementNS('http://www.w3.org/2000/svg', 'line');
        yAxis.setAttribute('x1', CENTER);
        yAxis.setAttribute('y1', '0');
        yAxis.setAttribute('x2', CENTER);
        yAxis.setAttribute('y2', SVG_SIZE);
        yAxis.setAttribute('stroke', '#3c2b1a');
        yAxis.setAttribute('stroke-width', '2');
        axesGroup.appendChild(yAxis);

        const tickValues = [-4, -3, -2, -1, 1, 2, 3, 4];
        tickValues.forEach(val => {
            if (val === 0) return;
            
            const xTick = document.createElementNS('http://www.w3.org/2000/svg', 'line');
            xTick.setAttribute('x1', toSvgX(val));
            xTick.setAttribute('y1', CENTER - 5);
            xTick.setAttribute('x2', toSvgX(val));
            xTick.setAttribute('y2', CENTER + 5);
            xTick.setAttribute('stroke', '#3c2b1a');
            xTick.setAttribute('stroke-width', '1');
            axesGroup.appendChild(xTick);

            const xLabel = document.createElementNS('http://www.w3.org/2000/svg', 'text');
            xLabel.setAttribute('x', toSvgX(val));
            xLabel.setAttribute('y', CENTER + 20);
            xLabel.setAttribute('text-anchor', 'middle');
            xLabel.setAttribute('font-size', '12');
            xLabel.setAttribute('fill', '#3c2b1a');
            xLabel.textContent = val;
            axesGroup.appendChild(xLabel);

            const yTick = document.createElementNS('http://www.w3.org/2000/svg', 'line');
            yTick.setAttribute('x1', CENTER - 5);
            yTick.setAttribute('y1', toSvgY(val));
            yTick.setAttribute('x2', CENTER + 5);
            yTick.setAttribute('y2', toSvgY(val));
            yTick.setAttribute('stroke', '#3c2b1a');
            yTick.setAttribute('stroke-width', '1');
            axesGroup.appendChild(yTick);

            const yLabel = document.createElementNS('http://www.w3.org/2000/svg', 'text');
            yLabel.setAttribute('x', CENTER - 20);
            yLabel.setAttribute('y', toSvgY(val) + 4);
            yLabel.setAttribute('text-anchor', 'end');
            yLabel.setAttribute('font-size', '12');
            yLabel.setAttribute('fill', '#3c2b1a');
            yLabel.textContent = val;
            axesGroup.appendChild(yLabel);
        });

        const zeroLabel = document.createElementNS('http://www.w3.org/2000/svg', 'text');
        zeroLabel.setAttribute('x', CENTER - 15);
        zeroLabel.setAttribute('y', CENTER + 15);
        zeroLabel.setAttribute('text-anchor', 'middle');
        zeroLabel.setAttribute('font-size', '12');
        zeroLabel.setAttribute('fill', '#3c2b1a');
        zeroLabel.textContent = '0';
        axesGroup.appendChild(zeroLabel);
    }

    function drawArea(r) {
        if (!areaGroup) return;
        areaGroup.innerHTML = '';
        drawAxes();

        const rect = document.createElementNS('http://www.w3.org/2000/svg', 'rect');
        rect.setAttribute('x', toSvgX(-r));
        rect.setAttribute('y', toSvgY(0));
        rect.setAttribute('width', r * SCALE);
        rect.setAttribute('height', r * SCALE);
        rect.setAttribute('fill', 'rgba(100,149,237,0.7)');
        rect.setAttribute('stroke', '#4169e1');
        rect.setAttribute('stroke-width', '1.5');
        areaGroup.appendChild(rect);

        const triangle = document.createElementNS('http://www.w3.org/2000/svg', 'polygon');
        triangle.setAttribute('points', [
            `${toSvgX(0)},${toSvgY(0)}`,
            `${toSvgX(-r/2)},${toSvgY(0)}`,
            `${toSvgX(0)},${toSvgY(r)}`
        ].join(' '));
        triangle.setAttribute('fill', 'rgba(100,149,237,0.7)');
        triangle.setAttribute('stroke', '#4169e1');
        triangle.setAttribute('stroke-width', '1.5');
        areaGroup.appendChild(triangle);

        const quarterCircle = document.createElementNS('http://www.w3.org/2000/svg', 'path');
        const radius = (r / 2) * SCALE;
        const d = `M ${toSvgX(0)} ${toSvgY(r/2)} A ${radius} ${radius} 0 0 1 ${toSvgX(r/2)} ${toSvgY(0)} L ${toSvgX(0)} ${toSvgY(0)} Z`;
        quarterCircle.setAttribute('d', d);
        quarterCircle.setAttribute('fill', 'rgba(100,149,237,0.7)');
        quarterCircle.setAttribute('stroke', '#4169e1');
        quarterCircle.setAttribute('stroke-width', '1.5');
        areaGroup.appendChild(quarterCircle);
    }

    function plotPointAt(x, y, hit, options = {}) {
        if (!pointsGroup) return;

        const { temporary = false } = options;
        const circle = document.createElementNS('http://www.w3.org/2000/svg', 'circle');
        circle.setAttribute('cx', toSvgX(x));
        circle.setAttribute('cy', toSvgY(y));
        circle.setAttribute('r', temporary ? '5' : '4');
        circle.setAttribute('fill', hit ? '#2b7a0b' : '#b00020');
        circle.setAttribute('stroke', '#fff');
        circle.setAttribute('stroke-width', '1');
        circle.dataset.origX = x;
        circle.dataset.origY = y;
        
        if (temporary && tempPointsGroup) {
            tempPointsGroup.appendChild(circle);
        } else {
            pointsGroup.appendChild(circle);
        }
        return circle;
    }

    function setElementValueAndTrigger(el, value) {
        if (!el) return;
        try {
            el.value = value;
            ['input', 'change', 'blur'].forEach(evtName => {
                try {
                    const ev = new Event(evtName, { bubbles: true });
                    el.dispatchEvent(ev);
                } catch (e) {
                    const ev = document.createEvent('HTMLEvents');
                    ev.initEvent(evtName, true, false);
                    el.dispatchEvent(ev);
                }
            });
        } catch (e) {}
    }

    function findXInputElement() {
        return document.querySelector('#pointForm\\:xInput_input') ||
               document.querySelector('#pointForm\\:xInput input') ||
               document.querySelector('#pointForm\\:xInput');
    }

    function findHiddenYElement() {
        return document.querySelector('#pointForm\\:yHidden') ||
               document.querySelector('#pointForm\\:yHidden_input') ||
               document.querySelector('input[name="pointForm:yHidden"]');
    }

    function findHiddenRElement() {
        return document.querySelector('#pointForm\\:rHidden') ||
               document.querySelector('#pointForm\\:rHidden_input') ||
               document.querySelector('input[name="pointForm:rHidden"]');
    }

    function loadPointsFromTable() {
        storedPoints = [];
        let table = document.querySelector('#resultsTable tbody') ||
                    document.querySelector('#pointForm\\:resultsTable tbody');
        
        if (!table) {
            const allTables = document.querySelectorAll('table[id*="resultsTable"]');
            if (allTables.length > 0) {
                table = allTables[0].querySelector('tbody');
            }
        }
        
        if (!table) {
            const pfTable = document.querySelector('.ui-datatable-data');
            if (pfTable) table = pfTable;
        }
        
        if (!table) return;
        
        let rows = table.querySelectorAll('tr:not(.ui-datatable-header):not(.ui-datatable-footer):not(.ui-datatable-empty-message)');
        if (rows.length === 0) {
            rows = table.querySelectorAll('tr');
        }
        
        rows.forEach(row => {
            if (row.classList.contains('ui-datatable-header') || 
                row.classList.contains('ui-datatable-footer') ||
                row.classList.contains('ui-datatable-empty-message')) {
                return;
            }
            
            const cells = row.querySelectorAll('td');
            if (cells.length >= 5) {
                try {
                    const x = parseFloat(cells[0].textContent.trim().replace(',', '.'));
                    const y = parseFloat(cells[1].textContent.trim().replace(',', '.'));
                    const r = parseFloat(cells[2].textContent.trim().replace(',', '.'));
                    
                    if (!isNaN(x) && !isNaN(y) && !isNaN(r)) {
                        storedPoints.push({ x, y, r });
                    }
                } catch (e) {}
            }
        });
    }

    function plotAllPoints() {
        if (!pointsGroup) return;
        loadPointsFromTable();
        
        const existingPoints = Array.from(pointsGroup.querySelectorAll('circle'));
        existingPoints.forEach(circle => {
            const x = parseFloat(circle.dataset.origX);
            const y = parseFloat(circle.dataset.origY);
            const exists = storedPoints.some(p => 
                Math.abs(p.x - x) < 0.01 && Math.abs(p.y - y) < 0.01
            );
            if (!exists) {
                circle.remove();
            }
        });
        
        storedPoints.forEach(point => {
            const { x, y } = point;
            const computedHit = checkHitClient(x, y, currentR);
            
            const existingCircle = Array.from(pointsGroup.querySelectorAll('circle')).find(c => {
                const cx = parseFloat(c.dataset.origX);
                const cy = parseFloat(c.dataset.origY);
                return Math.abs(cx - x) < 0.01 && Math.abs(cy - y) < 0.01;
            });
            
            if (existingCircle) {
                existingCircle.setAttribute('fill', computedHit ? '#2b7a0b' : '#b00020');
                existingCircle.setAttribute('cx', toSvgX(x));
                existingCircle.setAttribute('cy', toSvgY(y));
            } else {
                const circle = document.createElementNS('http://www.w3.org/2000/svg', 'circle');
                circle.setAttribute('cx', toSvgX(x));
                circle.setAttribute('cy', toSvgY(y));
                circle.setAttribute('r', '4');
                circle.setAttribute('fill', computedHit ? '#2b7a0b' : '#b00020');
                circle.setAttribute('stroke', '#fff');
                circle.setAttribute('stroke-width', '1');
                circle.dataset.origX = x;
                circle.dataset.origY = y;
                pointsGroup.appendChild(circle);
            }
        });
    }

    function checkHitClient(x, y, r) {
        const inRectangle = x >= -r && x <= 0 && y >= -r && y <= 0;
        const inTriangle = x >= -r/2 && x <= 0 && y >= 0 && y <= 2 * x + r;
        const inQuarterCircle = x >= 0 && y >= 0 && (x * x + y * y <= (r * r) / 4);
        return inRectangle || inTriangle || inQuarterCircle;
    }

    function findNearestPoint(clickX, clickY) {
        if (storedPoints.length === 0) return null;
        
        let nearestPoint = null;
        let minDistance = Infinity;
        
        storedPoints.forEach(point => {
            const dx = point.x - clickX;
            const dy = point.y - clickY;
            const distance = Math.sqrt(dx * dx + dy * dy);
            if (distance < minDistance) {
                minDistance = distance;
                nearestPoint = point;
            }
        });
        
        return nearestPoint;
    }

    function handleSvgClick(event) {
        event.stopPropagation();
        
        let svgX, svgY;
        try {
            const pt = svg.createSVGPoint();
            pt.x = event.clientX;
            pt.y = event.clientY;
            const ctm = svg.getScreenCTM();
            const inv = ctm ? ctm.inverse() : null;
            const transformed = inv ? pt.matrixTransform(inv) : pt;
            svgX = transformed.x;
            svgY = transformed.y;
        } catch (e) {
            const rect = svg.getBoundingClientRect();
            const x = event.clientX - rect.left;
            const y = event.clientY - rect.top;
            const scale = SVG_SIZE / rect.width;
            svgX = x * scale;
            svgY = y * scale;
        }
        
        const mathX = (svgX - CENTER) / SCALE;
        const mathY = (CENTER - svgY) / SCALE;
        
        loadPointsFromTable();
        const nearestPoint = findNearestPoint(mathX, mathY);
        let selectedX, selectedY;
        
        if (nearestPoint) {
            const distance = Math.sqrt((nearestPoint.x - mathX)**2 + (nearestPoint.y - mathY)**2);
            if (distance < 0.2) {
                selectedX = nearestPoint.x;
                selectedY = nearestPoint.y;
            } else {
                selectedX = Math.round(mathX * 10) / 10;
                selectedY = Math.round(mathY);
            }
        } else {
            selectedX = Math.round(mathX * 10) / 10;
            selectedY = Math.round(mathY);
        }
        
        if (tempPointsGroup) tempPointsGroup.innerHTML = '';
        
        const dotVal = selectedX.toFixed(1);
        const yVal = String(Math.round(selectedY));
        
        const allXInputs = [
            '#pointForm\\:xInput',
            '#pointForm\\:xInput_input',
            '#pointForm\\:xInput input',
            'input[name="pointForm:xInput"]',
            'input[name$=":xInput"]',
            'input[id*="xInput"]'
        ];
        
        allXInputs.forEach(sel => {
            try {
                document.querySelectorAll(sel).forEach(el => {
                    if (el) {
                        el.value = '';
                        setTimeout(() => {
                            el.value = dotVal;
                            setElementValueAndTrigger(el, dotVal);
                        }, 0);
                    }
                });
            } catch (e) {}
        });
        
        const allYInputs = [
            '#pointForm\\:yHidden',
            '#pointForm\\:yHidden_input',
            'input[name="pointForm:yHidden"]',
            'input[name$=":yHidden"]',
            'input[id*="yHidden"]'
        ];
        
        allYInputs.forEach(sel => {
            try {
                document.querySelectorAll(sel).forEach(el => {
                    if (el) {
                        el.value = '';
                        setTimeout(() => {
                            el.value = yVal;
                            setElementValueAndTrigger(el, yVal);
                        }, 0);
                    }
                });
            } catch (e) {}
        });
        
        const ySliderEl = document.getElementById('ySlider');
        if (ySliderEl) {
            ySliderEl.value = yVal;
            ['input', 'change'].forEach(evtName => {
                ySliderEl.dispatchEvent(new Event(evtName, { bubbles: true }));
            });
        }
        
        const yValueSpan = document.querySelector('#pointForm\\:yValue');
        if (yValueSpan) {
            yValueSpan.textContent = yVal;
        }
        
        plotPointAt(selectedX, selectedY, checkHitClient(selectedX, selectedY, currentR), { temporary: true });

        const checkButton = document.querySelector('#pointForm\\:checkButton');
        if (checkButton) {
            setTimeout(() => {
                const finalXInput = findXInputElement();
                const finalYInput = findHiddenYElement();
                const finalX = finalXInput?.value || dotVal;
                const finalY = finalYInput?.value || yVal;
                
                if (finalX !== dotVal && finalXInput) {
                    finalXInput.value = dotVal;
                    setElementValueAndTrigger(finalXInput, dotVal);
                }
                if (finalY !== yVal && finalYInput) {
                    finalYInput.value = yVal;
                    setElementValueAndTrigger(finalYInput, yVal);
                }
                
                checkButton.click();
            }, 100);
        }
    }

    function updateGraph(fromServer = false) {
        const rSlider = document.getElementById('rSlider');
        const rHidden = document.querySelector('#pointForm\\:rHidden');
        
        if (rSlider && rSlider.value) {
            currentR = parseFloat(rSlider.value);
        } else if (rHidden && rHidden.value) {
            currentR = parseFloat(rHidden.value);
        }
        
        drawArea(currentR);
        
        if (fromServer) {
            let attempts = 0;
            const maxAttempts = 5;
            
            const tryUpdatePoints = () => {
                attempts++;
                loadPointsFromTable();
                
                if (storedPoints.length > 0 || attempts >= maxAttempts) {
                    plotAllPoints();
                    if (tempPointsGroup) {
                        tempPointsGroup.innerHTML = '';
                    }
                } else {
                    setTimeout(tryUpdatePoints, 50);
                }
            };
            
            setTimeout(tryUpdatePoints, 50);
        } else {
            const allCircles = pointsGroup.querySelectorAll('circle');
            allCircles.forEach(circle => {
                const x = parseFloat(circle.dataset.origX);
                const y = parseFloat(circle.dataset.origY);
                if (!isNaN(x) && !isNaN(y)) {
                    const computedHit = checkHitClient(x, y, currentR);
                    circle.setAttribute('fill', computedHit ? '#2b7a0b' : '#b00020');
                }
            });
        }
        
        if (tempPointsGroup) {
            Array.from(tempPointsGroup.children).forEach(c => {
                const ox = parseFloat(c.dataset.origX);
                const oy = parseFloat(c.dataset.origY);
                if (!isNaN(ox) && !isNaN(oy)) {
                    c.setAttribute('cx', toSvgX(ox));
                    c.setAttribute('cy', toSvgY(oy));
                    c.setAttribute('fill', checkHitClient(ox, oy, currentR) ? '#2b7a0b' : '#b00020');
                }
            });
        }
    }

    function updateYValue(value) {
        const yValue = Math.round(parseFloat(value));
        const yValueSpan = document.querySelector('#pointForm\\:yValue');
        const yHidden = findHiddenYElement();
        if (yValueSpan) {
            yValueSpan.textContent = yValue;
        }
        if (yHidden) {
            setElementValueAndTrigger(yHidden, String(value));
        }
        const ySlider = document.getElementById('ySlider');
        if (ySlider) {
            ySlider.value = yValue;
        }
    }

    function updateRValue(value) {
        const rValue = parseFloat(value);
        const rValueSpan = document.querySelector('#pointForm\\:rValue');
        const rHidden = findHiddenRElement();
        if (rValueSpan) {
            rValueSpan.textContent = rValue.toFixed(2);
        }
        if (rHidden) {
            setElementValueAndTrigger(rHidden, String(value));
        }
        currentR = rValue;
        const rSlider = document.getElementById('rSlider');
        if (rSlider) {
            rSlider.value = value;
        }
        updateGraph();
    }

    window.updateYValue = updateYValue;
    window.updateRValue = updateRValue;
    window.updateGraph = updateGraph;

    function init() {
        svg = document.getElementById('areaSvg');
        if (!svg) return;
        
        areaGroup = document.getElementById('areaGroup');
        axesGroup = document.getElementById('axesGroup');
        pointsGroup = document.getElementById('pointsGroup');
        tempPointsGroup = document.getElementById('tempPointsGroup');
        
        if (!areaGroup || !axesGroup || !pointsGroup) return;
        
        loadPointsFromTable();
        updateGraph(true);
        
        svg.addEventListener('click', handleSvgClick);
        
        const yHidden = findHiddenYElement();
        const rHidden = findHiddenRElement();
        const ySlider = document.getElementById('ySlider');
        const rSlider = document.getElementById('rSlider');
        
        if (yHidden && ySlider) {
            const yVal = yHidden.value || 0;
            ySlider.value = yVal;
            updateYValue(yVal);
            setElementValueAndTrigger(yHidden, yVal);
        }
        
        if (rHidden && rSlider) {
            const rVal = rHidden.value || 2;
            rSlider.value = rVal;
            updateRValue(rVal);
        }

        if (window.initClock) {
            window.initClock();
        }

        const checkButton = document.querySelector('#pointForm\\:checkButton');
        if (checkButton) {
            checkButton.addEventListener('click', function () {
                const xInput = findXInputElement();
                const yHidden = findHiddenYElement();
                const xVal = xInput ? parseFloat(xInput.value) : NaN;
                const yVal = yHidden ? parseFloat(yHidden.value) : NaN;
                
                if (!isNaN(xVal) && !isNaN(yVal)) {
                    if (tempPointsGroup) tempPointsGroup.innerHTML = '';
                    plotPointAt(xVal, yVal, checkHitClient(xVal, yVal, currentR), { temporary: true });
                }
            }, { passive: true });
        }
        
        const resultsTable = document.querySelector('#resultsTable') || 
                             document.querySelector('#pointForm\\:resultsTable');
        if (resultsTable) {
            let updateTimeout;
            const observer = new MutationObserver(() => {
                clearTimeout(updateTimeout);
                updateTimeout = setTimeout(() => {
                    loadPointsFromTable();
                    plotAllPoints();
                }, 150);
            });
            
            observer.observe(resultsTable, {
                childList: true,
                subtree: true,
                characterData: true
            });
        }
    }

    document.addEventListener('DOMContentLoaded', init);
})();

(function () {
    'use strict';

    let clockIntervalId = null;

    function formatDateTime(date) {
        try {
            return date.toLocaleString('ru-RU', { 
                year: 'numeric',
                month: 'long', 
                day: 'numeric',
                hour: '2-digit',
                minute: '2-digit',
                second: '2-digit'
            });
        } catch (e) {
            return date.toString();
        }
    }

    function updateClock() {
        const el = document.getElementById('pageClock');
        if (!el) return;
        el.textContent = formatDateTime(new Date());
    }

    window.initClock = function () {
        updateClock();
        if (clockIntervalId) {
            clearInterval(clockIntervalId);
        }
        clockIntervalId = setInterval(updateClock, 9000);
    };
})();
