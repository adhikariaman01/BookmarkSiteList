# Mongo DB

``` javascript
show dbs


use mycustomers

db.createUser({
	user:"aman",
	pwd:"1234",
	roles:["readWrite","dbAdmin"]
});



// Collection


db.createCollection('customers');

//  INSERTION 


db.customers.insert({});

db.customers.insert([{}]);

// LOOKUP

db.customer.find();
db.customer.find().preety();

// use object id instead of forst_name in real world
db.customers.update({first_name: "Sabina"},{first_name:"Sabina",last_name:"Koirala",gender:"female"});

/*Instead of mentioning just Gender Entire first_name, last_name and gender is mentioned as only mentioning gender will update entire object.
It can be handle by using $set */

db.customers.update({first_name: "Aman"},{$set:{gender:"Male"}});

// $inc  for incrementing

// $unset for removing field

```
