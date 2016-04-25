




echo $PATH
DB_PATH=/tmp/applifire/db/NFNXQTSVVIMXLMRTLAL6G/EFFC8025-1802-4C64-9398-E019E20D57ED
MYSQL=/usr/bin
USER=pro2
PASSWORD=pro2
HOST=localhost


echo 'drop db starts....'
$MYSQL/mysql -h$HOST -u$USER -p$PASSWORD -e "SOURCE $DB_PATH/drop_db.sql";
echo 'drop db ends....'