
gradlew build
java -cp "build/libs/labwork1.jar;libs/fastcgi-lib.jar" ru.rmntim.web.HttpServerMain


python -m http.server 3000 --directory static
http://localhost:3000/index.html




java -cp build\libs\labwork1.jar ru.rmntim.web.HttpServerMain

cd apache-setup\httpd-root\htdocs && python -m http.server 24000

ssh -L 1715:localhost:1715 s467509@helios.cs.ifmo.ru -p 2222

java -jar -DFCGI_PORT=1736 ~/httpd-root/fcgi-bin/app.jar