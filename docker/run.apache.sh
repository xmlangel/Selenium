docker run -dit --name my-apache-app -p 8080:80 -v "$PWD/htdocs":/usr/local/apache2/htdocs/ httpd:2.4
