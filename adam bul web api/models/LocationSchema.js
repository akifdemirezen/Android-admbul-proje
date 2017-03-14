//Schema for Location

   module.exports = (function LocationSchema () {

	var Mongoose = require('../db').mongoose;

    var Schema = require('mongoose').Schema;

	var LocationSchema = Schema ({			
         
    	
        il: {type: String, require:true},
	
    	ilce: {type:String, require:true}	
        
       
       
	});

	var collectionName = 'locations';
	//var LocationSCHEMA = Mongoose.Schema(schema);
	var Location = Mongoose.model(collectionName, LocationSchema);
	
	return Location;
}) ();