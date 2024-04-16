
docker stop dental_db && docker rm dental_db
docker run -d --name dental_db -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=dental_database -p 3308:3306 mysql:latest