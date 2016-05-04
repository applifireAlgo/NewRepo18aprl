




echo $PATH
DB_PATH=/tmp/applifire/db/0UPXZJJONGBDNT0MLBVXW/C29671BB-2CE0-4B05-B917-BE6231E11017
MYSQL=/usr/bin
USER=pro1
PASSWORD=pro1
HOST=localhost


echo 'drop db starts....'
$MYSQL/mysql -h$HOST -u$USER -p$PASSWORD -e "SOURCE $DB_PATH/drop_db.sql";
echo 'drop db ends....'