//This Controller deals with all functionalities of User

function userController () {
	
	var User = require('../models/UserSchema');
	var Location = require('../models/LocationSchema');

//Passport






// Creating New User

this.createUser = function(req, res,next,err) {

		var username=req.body.username;
		var password=req.body.password;
		var il=req.body.il;
		var ilce=req.body.ilce;
		var name=req.body.name;
		var surname=req.body.surname;
		var tel=req.body.tel;
		var age=req.body.age;
		var registerid = req.body.registerid;

       var lokasyon=new Location({
				                   il:il
		                      ,ilce:ilce
												  });




							lokasyon.save(function(err,son){
								var user=new User({ 
		                        username:username
	                        ,	password:password
		                      , name:name
		                      ,surname:surname
	                      	,tel:tel
													,age:age
													,registerid:registerid
													,location_id:son._id});

	
											if(err) console.log(err);

												user.save(function(err,result){

											if(err) console.log(err);
											else
											return res.send(result);

												});
											});
													}

//Populateeee

  this.getUser = function (req, res, next) {
      
		  User.find().populate('location_id','il ilce')
			.exec(function(err, result) {
      if (err) {
        console.log(err);
        return res.send({'error':err}); 
      }
      else {
        return res.send({'USERS':result});
      }
    });
  };
 
return this;
 
};

module.exports = new userController();

