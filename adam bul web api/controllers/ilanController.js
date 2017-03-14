//This Controller deals with all functionalities of Ilan

function ilanController () {
	
	var Ilan = require('../models/ilanSchema');
	var User = require('../models/UserSchema');
	var Location = require('../models/LocationSchema');
	
	// Creating New İlan
	this.createIlan = function (req, res, next) {
	
    
    var aciklama=req.body.aciklama;
    var gelenuserid=req.body.ilaniveren_id;
		var gelenlok_id=req.body.ilanlok_id;

          var ilan=new Ilan({

					  
						               ilaniveren_id:gelenuserid,
													 ilanlok_id:gelenlok_id,
				                   aciklama:aciklama
													 
												  });
 
         ilan.save(function(err,ilanresult){
							
											if(err) console.log(err);
	                    else
											return res.send(ilanresult);

												});
												
	}
		
		
		
		
	/*	ilan.create({}, function(err, result) {
			if (err) {
				console.log(err);
				return res.send({'error':err});	
			}
			else {
        return res.send({'result':result,'status':'successfully saved'});
      }
		});
	}; */

  // Fetching Details of Ilan
  this.getIlan = function (req, res, next) {

    Ilan.find().populate('ilanlok_id','il ilce').populate('ilaniveren_id','name surname tel')
			.exec(function(err, result) {
      if (err) {
        console.log(err);
        return res.send({'error':err}); 
      }
      else {
        return res.send({'Ilanlar':result});
      } 
    });
  };

return this;

};

module.exports = new ilanController();