//This Controller deals with all functionalities of Location

function locationController () {
	var location = require('../models/LocationSchema');
	
	// Creating New Location
	this.createLocation = function (req, res, next) {
		var userid  = req.params.userid;
		var il = req.params.il;
		var ilce = req.params.ilce;
		
		location.create({userid:userid,il:il,ilce:ilce}, function(err, result) {
			if (err) {
				console.log(err);
				return res.send({'error':err});	
			}
			else {
        return res.send({'result':result,'status':'successfully saved'});
      }
		});
	};

  // Fetching Details of Location
  this.getLocation = function (req, res, next) {
  
		  location.find({},function(err, result){
      if (err) {
        console.log(err);
        return res.send({'error':err}); 
      }
      else {
        console.log(result);
        return res.send({'location Details':result});
      }
    });
  };

return this;

};

module.exports = new locationController();