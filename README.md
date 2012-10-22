# For Developers in Hurry

## Create an Application 

rhc app create -a notebook -t jbossas-7 -l <openshift_login_email> -d

## Adding MongoDB and RockMongo Client Cartridge

```
rhc app cartridge add -a notebook -c mongodb-2.0
rhc app cartridge add -a notebook -c rockmongo-1.1
rhc app cartridge add -a notebook -c postgresql-8.4
```

## Pulling code from github and pushing to OpenShift

After you have created the application using rhc create app command and added MongoDB, RockMongoDB client, and PostgreSQL cartridges using rhc app cartridge add command you have to checkout the code from my github. To do that follow the steps mentioned below.

```
git remote add notebook -m master git://github.com/shekhargulati/notebook-part3.git
 
git pull -s recursive -X theirs notebook master
 
git push
```
