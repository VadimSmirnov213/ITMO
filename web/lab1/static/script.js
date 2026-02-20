document.addEventListener('DOMContentLoaded', function() {
    console.log('Script loaded - version 5.0 (POST)');
    
    const IS_LOCAL_TEST = location.hostname === 'localhost' && location.port === '3000';
    const BACKEND_URL = IS_LOCAL_TEST ? 'http://localhost:8080/api/hit' : '/api/hit';
    
    console.log('Mode:', IS_LOCAL_TEST ? 'LOCAL TEST (POST)' : 'HELIOS (POST)');
    console.log('Backend URL:', BACKEND_URL);
    
    const quickButtons = document.querySelectorAll('.quick-btn');
    const xInput = document.getElementById('x');
    
    quickButtons.forEach(button => {
        button.addEventListener('click', function() {
            xInput.value = this.dataset.value;
        });
    });
    
    function addResultToTable(x, y, r, result, serverTime) {
        const tableBody = document.getElementById('resultsTableBody');
        const timeToShow = serverTime
            ? new Date(serverTime).toLocaleTimeString()
            : new Date().toLocaleTimeString();
        
        const newRow = document.createElement('tr');
        newRow.innerHTML = `
            <td>${x}</td>
            <td>${y}</td>
            <td>${r}</td>
            <td>${timeToShow}</td>
            <td class="${result ? 'result-hit' : 'result-miss'}">${result ? 'П' : 'Н'}</td>
        `;
        
        tableBody.insertBefore(newRow, tableBody.firstChild);
        
        const rows = tableBody.querySelectorAll('tr');
        if (rows.length > 5) {
            tableBody.removeChild(rows[rows.length - 1]);
        }
        
    }
    
    const STORAGE_KEY = 'results';
    function loadResults() {
        try {
            const raw = localStorage.getItem(STORAGE_KEY);
            return raw ? JSON.parse(raw) : [];
        } catch (e) {
            console.warn('Failed to load results from storage:', e);
            return [];
        }
    }
    function saveResults(items) {
        try {
            localStorage.setItem(STORAGE_KEY, JSON.stringify(items));
        } catch (e) {
            console.warn('Failed to save results to storage:', e);
        }
    }

    const savedItems = loadResults();
    if (Array.isArray(savedItems) && savedItems.length) {
        savedItems.slice(0, 5).forEach(it => {
            addResultToTable(it.x, it.y, it.r, it.result, it.now);
        });
    }
    
    const form = document.getElementById('mainForm');
    form.addEventListener('submit', function(e) {
        e.preventDefault();
        
        const xValue = xInput.value;
        const yValue = document.querySelector('input[name="y"]:checked')?.value;
        const rValue = document.getElementById('r').value;
        
        if (!xValue || isNaN(xValue) || parseFloat(xValue) < -5 || parseFloat(xValue) > 3) {
            alert('Введите корректное значение X (от -5 до 3)');
            return;
        }
        
        if (!yValue) {
            alert('Выберите значение Y');
            return;
        }
        
        if (!rValue) {
            alert('Выберите значение R');
            return;
        }
        
        
        const requestData = {
            x: xValue,  
            y: yValue,  
            r: rValue   
        };
        console.log('Request data (JSON):', requestData);
        
        fetch(BACKEND_URL, {
            method: 'POST',
            mode: 'cors',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Cache-Control': 'no-cache'
            },
            body: JSON.stringify(requestData)
        })
        .then(async (resp) => {
            console.log('Response status:', resp.status);
            console.log('Response headers:', resp.headers);
            
            let data;
            try {
                data = await resp.json();
                console.log('Parsed data:', data);
            } catch (e) {
                console.error('JSON parse error:', e);
                throw new Error('Некорректный ответ сервера');
            }
            
            if (resp.ok) {
                const isHit = !!data.result;
                addResultToTable(xValue, yValue, rValue, isHit, data.now);
                const items = loadResults();
                items.unshift({ x: xValue, y: yValue, r: rValue, result: isHit, now: data.now });
                saveResults(items.slice(0, 50)); 
                
                console.log('X:', xValue, 'Y:', yValue, 'R:', rValue, 'Результат:', isHit ? 'Попадание' : 'Промах');
            } else {
                const reason = data?.reason || 'Ошибка запроса';
                alert(`Ошибка: ${reason}`);
            }
        })
        .catch((err) => {
            console.error('Fetch error:', err);
            console.error('Error details:', err.message);
            console.error('Error stack:', err.stack);
            alert('Ошибка: ' + err.message + '\nURL: ' + BACKEND_URL);
        });
    });
});
