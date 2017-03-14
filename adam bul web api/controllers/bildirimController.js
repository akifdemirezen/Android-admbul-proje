//This Controller deals with all functionalities of Bildirim

function bildirimController () {
	var Ilan = require('../models/ilanSchema');
	var User = require('../models/UserSchema');
	var Location = require('../models/LocationSchema');
  var Bildirim = require('../models/bildirimSchema');
	
	// Creating New Bildirim
	this.gonderilecekbildirim = function (req, res, next) {
		

		var ilanid = req.body.ilan_id;
		var ilangidecekid = req.body.ilangidecek_id;
		var mesaj=req.body.mesaj;
    
 var bildirim = new Bildirim({
						               ilan_id:ilanid,
													 ilangidecek_id:ilangidecekid,
													 mesaj:mesaj
												  });

													 bildirim.save(function(err,bildirimresult){
							
											if(err) console.log(err);
	                    else
											return res.send(bildirimresult);

												});
												
	}




		
	/*	bildirim.create({}, function(err, result) {
			if (err) {
				console.log(err);
				return res.send({'error':err});	
			}
			else {
        return res.send({'result':result,'status':'successfully saved'});
      }
		});
	}; */

  // Fetching Details of Bildirim
  this.gelenbildirim = function (req, res, next) {

    Bildirim.find().populate('ilan_id','aciklama ').populate('ilangidecek_id','name')
		.populate('hangisemt','il ilce')	
			.exec(function(err, result) {
      if (err) {
        console.log(err);
        return res.send({'error':err}); 
      }
      else {
        return res.send({'bildirim Details':result});
      }
    });
  };

return this;

};

module.exports = new bildirimController();