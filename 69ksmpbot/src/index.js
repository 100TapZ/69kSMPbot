/** @format */

//clears the console
console.clear();

//bot requirements

const Client = require("./Structures/Client.js");

const config = require("./Data/config.json");

const client = new Client();

//if you are not using replit, remove this

const express = require('express');
const app = express();
const router = express.Router();
const path = require('path');


router.get('/', function(req, res){
  res.sendFile(path.join(__dirname, '/index.html'));
});
app.use('/', router);

app.use(function(req, res, next) {
    res.status(404);
    res.sendFile(__dirname + '/404.html');
});


let server = app.listen(3000, function(){
  console.log("App server is running on port 3000");
});

//bot start

client.start(config.token);