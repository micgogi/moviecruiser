cd moviecruiserauthenticationservice
source ./env-variable.sh
mvn clean package
docker build -t user-app .
cd ..
cd moviecruizerapplication
source ./env-variable.sh
mvn clean package
docker build -t movie-app .
cd ..
