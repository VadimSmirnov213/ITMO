<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Проверка попадания точки в область</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>Веб-программирование, ЛР-2, Вариант-6283</h1>
            <p><strong>Студент:</strong> Вадим</p>
            <p><strong>Группа:</strong> P3219</p>
        </div>

        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) {
        %>
        <div class="error-message" style="background: #FFEBEE; color: #D32F2F; padding: 15px; border-radius: 8px; margin-bottom: 20px; text-align: center;">
            <strong><%= errorMessage %></strong>
        </div>
        <%
            }
        %>

        <div class="form-container">
            <div class="form-section">
                <h2>Параметры точки</h2>
                <form id="pointForm" method="GET" action="controller">
                    <div class="form-group">
                        <label>Введите X:</label>
                        <div class="radio-group">
                            <input type="radio" name="x" value="-4" id="x-4">
                            <label for="x-4">-4</label>
                            
                            <input type="radio" name="x" value="-3" id="x-3">
                            <label for="x-3">-3</label>
                            
                            <input type="radio" name="x" value="-2" id="x-2">
                            <label for="x-2">-2</label>
                            
                            <input type="radio" name="x" value="-1" id="x-1">
                            <label for="x-1">-1</label>
                            
                            <input type="radio" name="x" value="0" id="x0">
                            <label for="x0">0</label>
                            
                            <input type="radio" name="x" value="1" id="x1">
                            <label for="x1">1</label>
                            
                            <input type="radio" name="x" value="2" id="x2">
                            <label for="x2">2</label>
                            
                            <input type="radio" name="x" value="3" id="x3">
                            <label for="x3">3</label>
                            
                            <input type="radio" name="x" value="4" id="x4">
                            <label for="x4">4</label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="y">Координата Y (-3 ... 5):</label>
                        <input type="text" id="y" name="y" placeholder="Введите Y" required>
                    </div>

                    <div class="form-group">
                        <label for="r">Радиус R (1 ... 4):</label>
                        <input type="text" id="r" name="r" placeholder="Введите R" required>
                    </div>

                    <button type="submit" class="submit-btn">ПРОВЕРИТЬ</button>
                </form>
            </div>

            <div class="form-section">
                <h3>Интерактивная область</h3>
                <div class="interactive-area">
                    <svg id="areaSvg" width="300" height="300" viewBox="-150 -150 300 300" 
                         class="area-image" onclick="handleAreaClick(event)">
                        <line x1="-150" y1="0" x2="150" y2="0" stroke="#333" stroke-width="2"/>
                        <line x1="0" y1="-150" x2="0" y2="150" stroke="#333" stroke-width="2"/>
                        
                        <text x="140" y="-5" fill="#333" font-size="12">X</text>
                        <text x="5" y="-140" fill="#333" font-size="12">Y</text>
                        
                        <g id="axisLabels"></g>
                        
                        <g id="hitArea"></g>
                        
                        <g id="resultPoints"></g>
                    </svg>
                    
                    <div id="clickInfo" class="click-info"></div>
                </div>
            </div>
        </div>

        <div class="results-section">
            <h2>Результаты</h2>
            <table class="results-table" id="resultsTable">
                <thead>
                    <tr>
                        <th>X</th>
                        <th>Y</th>
                        <th>R</th>
                        <th>Время</th>
                        <th>Рез.</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        @SuppressWarnings("unchecked")
                        List<org.example.model.PointDTO> results = 
                            (List<org.example.model.PointDTO>) request.getAttribute("results");
                        
                        if (results == null || results.isEmpty()) {
                    %>
                    <tr>
                        <td colspan="5" style="text-align: center; color: #667;">
                            Пока нет результатов проверок
                        </td>
                    </tr>
                    <%
                        } else {
                            int startIndex = Math.max(0, results.size() - 10);
                            for (int i = results.size() - 1; i >= startIndex; i--) {
                                org.example.model.PointDTO result = results.get(i);
                    %>
                    <tr>
                        <td><%= result.getX() %></td>
                        <td><%= result.getY() %></td>
                        <td><%= result.getR() %></td>
                        <td><%= result.getTimestamp() %></td>
                        <td class="<%= result.isHit() ? "hit" : "miss" %>">
                            <%= result.isHit() ? "ПОПАЛ" : "МИМО" %>
                        </td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>

        <div class="footer">
            <p>ИТМО 2025</p>
        </div>
    </div>

    <script src="js/validation.js"></script>
</body>
</html>
