console.log('validation.js загружен');

document.addEventListener('DOMContentLoaded', function() {
    console.log('DOM загружен, инициализируем...');
    const form = document.getElementById('pointForm');
    const yInput = document.getElementById('y');
    const rInput = document.getElementById('r');
    const svg = document.getElementById('areaSvg');
    
    const title = document.querySelector('h3');
    if (title && title.textContent === 'Интерактивная область') {
        const coordsSpan = document.createElement('span');
        coordsSpan.id = 'mouseCoords';
        coordsSpan.style.marginLeft = '10px';
        coordsSpan.style.fontSize = '14px';
        coordsSpan.style.color = '#666';
        title.appendChild(coordsSpan);
    }
    
    if (svg) {
        svg.addEventListener('mousemove', function(event) {
            const rect = svg.getBoundingClientRect();
            const x = event.clientX - rect.left;
            const scale = getScale();
            const areaX = (x - 150) / scale;
            document.getElementById('mouseCoords').textContent = `X: ${areaX.toFixed(4)}`;
        });
        
        svg.addEventListener('mouseleave', function() {
            document.getElementById('mouseCoords').textContent = '';
        });
    }
    
    console.log('Элементы найдены:', {
        form: !!form,
        yInput: !!yInput,
        rInput: !!rInput,
        svg: !!svg
    });
    
    if (form) {
        form.addEventListener('submit', function(e) {
            if (!validateForm()) {
                e.preventDefault();
            }
        });
    }
    
    if (yInput) {
        yInput.addEventListener('input', validateY);
    }
    if (rInput) {
        rInput.addEventListener('input', function() {
            console.log('r input event, value=', rInput.value);
            validateR();
            updateArea();
            restoreResultPoints();
        });
        rInput.addEventListener('change', function() {
            console.log('r change event, value=', rInput.value);
            validateR();
            updateArea();
            restoreResultPoints();
        });
    }
});

function validateForm() {
    const xSelected = document.querySelector('input[name="x"]:checked');
    const yValue = document.getElementById('y').value;
    const rValue = document.getElementById('r').value;
    
    let isValid = true;
    
    if (!xSelected) {
        showError('Выберите координату X');
        isValid = false;
    }
    
    if (!validateY()) {
        isValid = false;
    }
    
    if (!validateR()) {
        isValid = false;
    }
    
    return isValid;
}

function validateY() {
    const yInput = document.getElementById('y');
    const yValue = parseFloat(yInput.value);
    
    if (isNaN(yValue)) {
        if (yInput.value.trim() !== '') {
            showFieldError(yInput, 'Y должно быть числом');
            return false;
        }
    } else if (yValue < -3 || yValue > 5) {
        showFieldError(yInput, 'Y должно быть от -3 до 5');
        return false;
    } else {
        clearFieldError(yInput);
        return true;
    }
    
    return true;
}

function validateR() {
    const rInput = document.getElementById('r');
    const rValue = parseFloat(rInput.value);
    
    if (isNaN(rValue)) {
        if (rInput.value.trim() !== '') {
            showFieldError(rInput, 'R должно быть числом');
            return false;
        }
    } else if (rValue < 1 || rValue > 4) {
        showFieldError(rInput, 'R должно быть от 1 до 4');
        return false;
    } else {
        clearFieldError(rInput);
        return true;
    }
    
    return true;
}

function showFieldError(input, message) {
    input.classList.add('error');
    input.title = message;
}

function clearFieldError(input) {
    input.classList.remove('error');
    input.title = '';
}

function showError(message) {
    alert(message);
}

function handleAreaClick(event) {
    const rValue = document.getElementById('r').value;
    
    if (!rValue || rValue.trim() === '') {
        showClickInfo('Сначала установите радиус R!');
        return;
    }

    const svg = document.getElementById('areaSvg');
    const rect = svg.getBoundingClientRect();
    
    const x = event.clientX - rect.left;
    const y = event.clientY - rect.top;
    
    const scale = getScale(); 
    const areaX = (x - 150) / scale; 
    const areaY = (150 - y) / scale; 
    
    const roundedX = Math.round(areaX);
    if (roundedX < -4 || roundedX > 4) {
        showClickInfo('X должен быть в диапазоне от -4 до 4!');
        return;
    }
    
    const r = parseFloat(rValue);
    const hit = checkPointInArea(areaX, areaY, r);
    
    const xRadio = document.querySelector(`input[name="x"][value="${roundedX}"]`);
    if (xRadio) {
        xRadio.checked = true;
    }
    
    const yInput = document.getElementById('y');
    if (yInput) {
        yInput.value = areaY.toFixed(2);
    }
    
    const result = hit ? 'ПОПАЛ' : 'МИМО';
    showClickInfo(`X=${roundedX}, Y=${areaY.toFixed(2)}, R=${r} → ${result}`);
    
    addResultPoint(areaX, areaY, hit);
    
    document.getElementById('pointForm').submit();
}

function addResultPoint(x, y, hit) {
    const resultPoints = document.getElementById('resultPoints');
    const scale = getScale();
    
    const xPx = x * scale;
    const yPx = -y * scale;
    
    const color = hit ? '#2E7D32' : '#D32F2F';
    resultPoints.innerHTML += `<circle cx="${xPx}" cy="${yPx}" r="3" fill="${color}" stroke="${color}" stroke-width="2"/>`;
}

function checkPointInArea(x, y, r) {
    if (x <= 0 && y >= 0) {
        return (x * x + y * y) <= (r * r / 4);
    }
    
    if (y <= 0 && y >= x - r/2 && x >= -r/2 && x <= r/2) {
        return true;
    }
    
    if (x >= 0 && y <= 0 && x <= r/2 && y >= -r) {
        return true;
    }
    
    return false;
}

function showClickInfo(message) {
    const infoDiv = document.getElementById('clickInfo');
    infoDiv.textContent = message;
    infoDiv.style.display = 'block';
    
    setTimeout(() => {
        infoDiv.style.display = 'none';
    }, 3000);
}

document.addEventListener('DOMContentLoaded', function() {
    console.log('JavaScript загружен');
    updateArea();
    restoreResultPoints();
});

function getScale() {
    const base = 150 / 4;
    return base;
}

function updateArea() {
    console.log('updateArea вызвана');
    
    const axisLabels = document.getElementById('axisLabels');
    const hitArea = document.getElementById('hitArea');
    
    if (!axisLabels || !hitArea) {
        console.error('Элементы axisLabels или hitArea не найдены!');
        return;
    }
    
    const rValue = document.getElementById('r').value;
    console.log('R значение:', rValue);
    
    axisLabels.innerHTML = '';
    hitArea.innerHTML = '';
    
    addBasicAxisLabels();
    
    let r = parseFloat(rValue);
    if (isNaN(r) || r < 1 || r > 4) {
        r = 2;
    }
    
    console.log('Рисуем область для R =', r);
    
    const scale = getScale();
    
    const radius = r / 2;
    const radiusPx = radius * scale;
    const rPx = r * scale;
    
    const centerX = 0;
    const centerY = 0;
    const centerXPx = centerX * scale;
    const centerYPx = -centerY * scale;
    
    hitArea.innerHTML += 
        `<path d="M 0 0 L -${radiusPx} 0 A ${radiusPx} ${radiusPx} 0 0 1 0 -${radiusPx} Z" 
         fill="#6496FF" stroke="#6496FF" stroke-width="2"/>`;
    
    const x1 = -radiusPx;
    const y1 = 0;
    const x2 = 0;
    const y2 = 0;
    const x3 = radiusPx;
    const y3 = 0;
    const x4 = radiusPx;
    const y4 = rPx;
    const x5 = 0;
    const y5 = rPx;
    const x6 = 0;
    const y6 = radiusPx;
    
    hitArea.innerHTML += 
        `<path d="M ${x1} ${y1} L ${x2} ${y2} L ${x3} ${y3} L ${x4} ${y4} L ${x5} ${y5} L ${x6} ${y6} L ${x1} ${y1} Z" 
         fill="#6496FF" stroke="#6496FF" stroke-width="2"/>`;
    
    console.log('Область нарисована');
    restoreResultPoints();
}

function drawAxes() {
    const axisLabels = document.getElementById('axisLabels');
    const scale = getScale();
    
    axisLabels.innerHTML += `<line x1="-150" y1="0" x2="150" y2="0" stroke="#000" stroke-width="3"/>`;
    axisLabels.innerHTML += `<line x1="0" y1="-150" x2="0" y2="150" stroke="#000" stroke-width="3"/>`;
    
    axisLabels.innerHTML += `<polygon points="150,0 140,-8 140,8" fill="#000"/>`;
    axisLabels.innerHTML += `<polygon points="0,-150 -8,-140 8,-140" fill="#000"/>`;
    
    axisLabels.innerHTML += `<text x="160" y="8" fill="#000" font-size="16" font-weight="bold">X</text>`;
    axisLabels.innerHTML += `<text x="8" y="-160" fill="#000" font-size="16" font-weight="bold">Y</text>`;
}

function addBasicAxisLabels() {
    const axisLabels = document.getElementById('axisLabels');
    const scale = getScale();
    
    drawAxes();
    for (let i = -4; i <= 4; i++) {
        if (i === 0) continue;
        const x = i * scale;
        axisLabels.innerHTML += `<line x1="${x}" y1="-150" x2="${x}" y2="150" stroke="#888" stroke-width="1" stroke-dasharray="4,4"/>`;
    }
    for (let i = -4; i <= 4; i++) {
        if (i === 0) continue;
        const x = i * scale;
        axisLabels.innerHTML += `<line x1="${x}" y1="-5" x2="${x}" y2="5" stroke="#666" stroke-width="1"/>`;
        axisLabels.innerHTML += `<text x="${x}" y="15" fill="#666" font-size="10" text-anchor="middle">${i}</text>`;
        const y = -i * scale;
        axisLabels.innerHTML += `<line x1="-5" y1="${y}" x2="5" y2="${y}" stroke="#666" stroke-width="1"/>`;
        axisLabels.innerHTML += `<text x="-15" y="${y + 3}" fill="#666" font-size="10" text-anchor="middle">${i}</text>`;
    }
}

function restoreResultPoints() {
    console.log('Восстанавливаем точки результатов...');
    
    const resultsTable = document.getElementById('resultsTable');
    if (!resultsTable) {
        console.log('Таблица результатов не найдена');
        return;
    }
    
  
    const resultPoints = document.getElementById('resultPoints');
    resultPoints.innerHTML = '';
    
    let currentR = parseFloat(document.getElementById('r').value);
    if (isNaN(currentR) || currentR < 1 || currentR > 4) {
        currentR = 2;
    }

    const rows = resultsTable.querySelectorAll('tbody tr');
    console.log('Найдено строк результатов:', rows.length);
    
    rows.forEach((row, index) => {
        const cells = row.querySelectorAll('td');
        console.log('Обрабатываем строку:', index, 'Количество ячеек:', cells.length);
        
        if (cells.length >= 2) {
            const x = parseFloat(cells[0].textContent);
            const y = parseFloat(cells[1].textContent);
            console.log('Значения точки:', { x, y, currentR });
            
            if (!isNaN(x) && !isNaN(y) && !isNaN(currentR)) {
                const isHit = checkPointInArea(x, y, currentR);
                console.log('Добавляем точку:', { x, y, currentR, isHit });
                addResultPoint(x, y, isHit);
            }
        }
    });
    
    console.log('Точки результатов восстановлены');
}

function getScale() {
    const base = 150 / 4;
    return base;
}