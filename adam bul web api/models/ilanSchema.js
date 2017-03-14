//Schema for İlan

   module.exports = (function Ilanschema () {

	var Mongoose = require('../db').mongoose;
    var Schema=Mongoose.Schema;
	
	var Ilanschema = Schema({
	
		
		ilaniveren_id :{type : Schema.ObjectId , ref : 'users'},
		ilanlok_id:{type: Schema.ObjectId ,ref : 'locations'},
		aciklama:{type:String,required:true},
		
		
	});
	
	var collectionName = 'ilans';
	
	var Ilan = Mongoose.model(collectionName, Ilanschema);
	
	return Ilan;
})();