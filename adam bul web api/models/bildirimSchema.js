//Schema for Bildirim

   module.exports = (function bildirimSchema () {

	var Mongoose = require('../db').mongoose;
    var Schema=Mongoose.Schema;

	var Bildirimschema = Schema({
		
		ilan_id :{type:Schema.ObjectId,ref : 'ilans'},
		ilangidecek_id:{type : Schema.ObjectId, ref : 'users'},
	    mesaj:{type:String,require:true}
		//hangisemt:{type : Schema.ObjectId, ref : 'locations'}
	
		
	});
	
	var collectionName = 'bildirims';
	
	var Bildirim = Mongoose.model(collectionName, Bildirimschema);
	
	return Bildirim;
})();