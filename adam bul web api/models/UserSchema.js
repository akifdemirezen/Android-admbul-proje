// Model for the User 
     module.exports = (function userSchema () {

	var Mongoose = require('../db').mongoose;
	var Schema=Mongoose.Schema;
    
	

	var userSchema = Schema({ 
        username :{type:String,require:true},
		password :{type:String,require:true},
		surname: {type: String,require:true},
	    name:{type: String,require:true},
		tel: {type: String,require:true},
		age: {type: String,require:true},
		registerid:{type:String,require:true},	
	    location_id: { type : Schema.Types.ObjectId, ref: 'locations' }
 	
	
});

	var collectionName = 'users';	
	
	
	var User = Mongoose.model(collectionName, userSchema);
	
	return User;

	
})();