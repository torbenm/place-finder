# Points of Interest

This branch is solely existing to store the Points of Interst Database.
We'll see if it makes sense to merge it with other branches later on.

## How to generate the Database

1. [Download the full OSM.PBF Data for Germany](http://ftp5.gwdg.de/pub/misc/openstreetmap/download.geofabrik.de/germany-latest.osm.pbf)

2. Install necessary packages

  ```
  brew install Caskroom/cask/java
  brew install osmosis
  brew install python
  ```

3. Run the python script

  ```
  python export.py -i "germany-latest.osm.pbf" -ox "amenities.xml" -oj "amenities.json"
  ```
  This should take a while, as XML & JSON files of around 80 MB are created.

## Loading it into MongoDB

  To see whether it is faster/just as fast with mongodb, here are the instructions for
  loading it into there:

  1. Installation
    ```
    brew install mongodb
    mongod
    ```

  2. Load the data. 

  ```
  mongoimport --db poi-database --collection amenities --type json --file amenities.json --jsonArray
  ```

  3. Create 2dsphere index
  ```
  mongo
  > use poi-database
  > db.amenities.ensureIndex({location:"2dsphere"});
  ```

  4. Let's test it

  ```
  > db.runCommand(
   {
     geoNear: "amenities",
     near: { type: "Point", coordinates: [ 8.745657, 51.718630 ] },
     spherical: true,
     maxDistance: 100
   }
  )
  ```

  ```json
  {
	"waitedMS" : NumberLong(0),
	"results" : [
		{
			"dis" : 22.25357183506832,
			"obj" : {
				"_id" : ObjectId("572a849e2f14b5494302c531"),
				"amenity" : "pub",
				"address" : {
					"country" : "DE",
					"street" : "Fürstenbergstraße",
					"housenumber" : "32a",
					"postcode" : "33102",
					"city" : "Paderborn"
				},
				"name" : "Willie's Heartbreak Hotel",
				"location" : {
					"type" : "Point",
					"coordinates" : [
						8.7459276,
						51.7187389
					]
				}
			}
		},
		{
			"dis" : 47.17066728086284,
			"obj" : {
				"_id" : ObjectId("572a84a02f14b5494303aeef"),
				"amenity" : "pub",
				"address" : {
					"country" : "DE",
					"street" : "Imadstraße",
					"housenumber" : "7",
					"postcode" : "33102",
					"city" : "Paderborn"
				},
				"name" : "Sputnik",
				"location" : {
					"type" : "Point",
					"coordinates" : [
						8.7457501,
						51.7182102
					]
				}
			}
		},
		{
			"dis" : 85.10180501646384,
			"obj" : {
				"_id" : ObjectId("572a84a02f14b5494303aef0"),
				"amenity" : "fast_food",
				"address" : {
					"country" : "DE",
					"street" : "Theodorstraße",
					"housenumber" : "8",
					"postcode" : "33102",
					"city" : "Paderborn"
				},
				"name" : "Pizzeria Sicilia",
				"location" : {
					"type" : "Point",
					"coordinates" : [
						8.7455265,
						51.7193902
					]
				}
			}
		}
	],
	"stats" : {
		"nscanned" : 21,
		"objectsLoaded" : 5,
		"avgDistance" : 51.508681377464995,
		"maxDistance" : 85.10180501646384,
		"time" : 2
	},
	"ok" : 1
}
  ```
