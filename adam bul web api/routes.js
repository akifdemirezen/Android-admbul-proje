    var user = require('./controllers/userController');
	var location=require('./controllers/locationController');
	var ilan=require('./controllers/ilanController');
	var bildirim=require('./controllers/bildirimController');
    
	//Import dependencies
	var passport = require('passport');  
    var express = require('express');  
    
    var jwt = require('jsonwebtoken');
	var User = require('../rs/models/UserSchema');
	var router=express.Router();
 
    var request = 	require('request');
   

module.exports = function(app) {
	
app.post('/send', function(req, res, next) {
 
	var sendNotification = function(data) {

  var headers = {
    "Content-Type": "application/json; charset=utf-8",
    "Authorization": "Basic YjE2MDVkMWQtOTFhZS00MTU5LTk3NTUtZGRmNWYyYWFjNWFi"
  };
  
  var options = {
    host: "onesignal.com",
    port: 443,
    path: "/api/v1/notifications",
    method: "POST",
    headers: headers,
  };
  
  var https = require('https');
  var req = https.request(options, function(res) {  
    res.on('data', function(data) {
      console.log("Response:");
      console.log(JSON.parse(data));
    });
  });
  
  req.on('error', function(e) {
    console.log("ERROR:");
    console.log(e);
  });
  
  req.write(JSON.stringify(data));
  req.end();
};

var contents=req.body.contents;
var included_segments=req.body.included_segments;

var message = { 
  app_id: "bff09b26-ecde-446d-a8ff-0b83f23edeab",
  contents: {en: contents},
  included_segments: included_segments
};

sendNotification(message);
     
	       });



	  app.post('/registerkaydet', function(req,res){

	var restKey = 'YjE2MDVkMWQtOTFhZS00MTU5LTk3NTUtZGRmNWYyYWFjNWFi';
	var appID = 'bff09b26-ecde-446d-a8ff-0b83f23edeab';
   // var identifier=req.body.identifier;
    request(
		{
			method:'POST',
			uri:'https://onesignal.com/api/v1/players',
			headers: {
				"authorization": "Basic "+restKey,
				"content-type": "application/json"
			},
			json: true,
			body:{
				'app_id': appID,
				'device_type': 1,
				'ad_id':"Advertising Id"

			}
		},
		function(error, response, body) {
			if(!body.errors){
				res.send(body);
			}else{
				console.error('Error:', body.errors);
			}
			
		}
	);
	
 
      });

   


        app.post('/login', function(req, res) {
            User.findOne({username: req.body.username, password: req.body.password}, function(err, user) {
                if (err) {
                    res.json({
                        type: false,
                        data: "Error occured: " + err
                    });
                } else {
                    if (user) {
                        if(user.username==req.body.username && user.password==req.body.password){
                    res.json({
                            type: true,
                            data: user._id
                            
                        }); }
                    } else {
                        res.json({
                            type: false,
                            data: "Incorrect username/password"
                        });    
                    }
                }
            });
        });

       app.get('/', function(req, res, next) {
                return res.send("WELCOME TO REST API");
            });

        //User get and create
	  
	
       app.get('/getUser',user.getUser);
	    app.post('/createUser', user.createUser); //Create Student API
		
          //Ilan get and create

		app.post('/createIlan',ilan.createIlan);
		app.get('/getIlan',ilan.getIlan);
	    
		      //Location	
	     app.get('/getlocation', location.getLocation);
		 //Bildirim
		 app.post('/bildirimgonder',bildirim.gonderilecekbildirim);
		 app.get('/bildirimgoster',bildirim.gelenbildirim);

 
};

	


	
