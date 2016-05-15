/****
 * @author torben.meyer@mailbox.org
 * @version 1.0.0
 */

var restify = require('restify');

function placesByLatLng(req, res, next) {
  res.send( req);
  next();
}
function placeById(req, res, next){
  res.send("Requested Place By Id");
  next();
}
function placeByLatLng(req, res, next){
  res.send("Requested Place By Lat Lng")
  next();
}

var server = restify.createServer(
  {
    name:"Place Finder",
    version: "1.0.0"
  });

server.get('/places/:lat/:lng', placesByLatLng);
server.get('/place/:id', placeById)
server.get('/place/:lat/:lng', placeByLatLng);

server.listen(8080, function() {
  console.log('%s listening at %s', server.name, server.url);
});

/* Server API
  host/places/lat,lng/radius    -- All places in a certain radius around some lat/lng
  host/place/id                 -- One place with a certain ID
  host/place/lat,lng            -- The nearest place with a certain lat,lng
*/
