




echo $PATH
DB_PATH=/tmp/applifire/db/VDX6CG8QYT2MBNSLSMQZW/B4AE4BD2-75F0-4FD8-AD20-AFFF45EDAC62
MYSQL=/usr/bin
USER=pro2
PASSWORD=pro2
HOST=localhost


echo 'drop db starts....'
$MYSQL/mysql -h$HOST -u$USER -p$PASSWORD -e "SOURCE $DB_PATH/drop_db.sql";
echo 'drop db ends....'