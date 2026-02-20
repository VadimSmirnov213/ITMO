
JETTY_HOME="${JETTY_HOME:-/c/jetty-distribution-9.4.54.v20240208}"

cd /c/servers/jetty-base || exit 1

if [ ! -f "$JETTY_HOME/start.jar" ]; then
    echo "Ошибка: start.jar не найден в $JETTY_HOME"
    echo ""
    echo "Найдите папку, где находится start.jar, и:"
    echo "1. Либо установите переменную: export JETTY_HOME=/ваш/путь/к/jetty-home"
    echo "2. Либо измените JETTY_HOME в этом скрипте"
    exit 1
fi

echo "Запуск Jetty из $JETTY_HOME"
export JETTY_HOME
java -jar "$JETTY_HOME/start.jar"

