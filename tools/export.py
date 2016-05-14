# -*- coding: utf-8 -*-

__AUTHOR__ = "torben.meyer@mailbox.org"
__VERSION__ = 1.0
__LAST_CHANGE__ = "Sat, May 14th, 2016"

import sys, os, json, io
from xml.dom.minidom import parse
import xml.dom.minidom

# SETTINGS
skip_to = -1
valid = 0
osm_file = "\germany-latest.osm.pbf"
xml_file = "amenities.xml"
json_file = "amenities.json"
verbose = true


if(len(sys.argv) > 1):
    if('-h' in sys.argv or '--help' in sys.argv):
        commands = {
            '-h, --help': 'Print this help message and exit',
            '--info': 'Prints the info about the tool',
            '-i': 'Defines the input file. Must be an .osm.pbf file',
            '-ix': 'Skip the first step and start with an xml file. See work flow of this script',
            '-ox': 'Defines the output file for the xml document',
            '-oj': 'Defines the output file for the parsed json document',
            '--be-quiet': 'Tool be quiet! Don\'t print any information'
        }
        print "usage: python export.py <OPTIONS>"
        print ""
        print "The following options are available: "
        for key in commands:
            print "{:15}{}".format(key, commands[key])
        print ""
        print "Workflow:"
        print "Typically, the export cycle consists of 2 steps. You can skip the first step by directly giving the xml file as input."
        print "However, this xml file must have come as an output of this tool"
        print "(1) Create an XML file out of the given .osm.pbf file"
        print "\tFor this, at least the options -i and -ox must be defined"
        print "(2) Create a JSON file out of the xml file."
        print "\tFor this, the -oj parameter must be given additionaly to the ones from step one."
        print "\tIf you already have an xml file, you can replace the parameters from (1) by just giving the -ix parameter"
        print "The json file can then be imported into mongodb"

    elif('--info' in sys.argv):
        info = {
            "Autor:": __AUTHOR__,
            "Version:":__VERSION__,
            "Last Change:":__LAST_CHANGE__
        }
        print "Creates a geo json file of amenities from a given Openstreetmap database. Works primarily with mongodb."
        for key in info:
            print "{:15}{}".format(key, info[key])
    else:
        if('--be-quiet' in sys.argv):
            verbose = false
        if('-ix' in sys.argv):
            xml_file = sys.argv[sys.argv.index('-ix')+1]
            skip_to = 2
        if('-i' in sys.argv):
            osm_file = sys.argv[sys.argv.index('-i')+1]
            skip_to = 0
        if('-ox' in sys.argv):
            if(skip_to == 0):
                xml_file = sys.argv[sys.argv.index('-ox')+1]
                valid = valid + 1
        if('-oj' in sys.argv):
            if(skip_to == 0 or skip_to == 2):
                json_file = sys.argv[sys.argv.index('-oj')+1]
                valid = valid + 1
        if((skip_to == 2 and valid != 1) or (skip_to == 0 and valid != 2)):
            print "ERROR: You gave an unsufficient amount of parameters."

amenities = [
    "bar",
    "bbq",
    "biergarten",
    "cafe",
    "fast_food",
    "pub",
    "restaurant",
    "college",
    "university",
    "bus_station",
    "ferry_terminal",
    "parking",
    "arts_centre",
    "casino",
    "cinema",
    "community_centre",
    "fountain",
    "gambling",
    "nightclub",
    "planetarium",
    "social_centre",
    "stripclub",
    "swingerclub",
    "theatre",
    "food_court",
    "ice_cream"
]

# OSM.PBF TO XML
if(skip_to <= 1):
    cmd = "osmosis --read-pbf {0} --node-key-value keyValueList=\"{1}\" --write-xml {2}".format(
        osm_file,
        "amenity."+",amenity.".join(amenities),
        xml_file
    )
    if(verbose)
        print "Executing "+ cmd
        print os.system(cmd)
        print "Created XML file!"

# XML TO JSON
if(skip_to <= 2):
    data = []
    DOMTree = xml.dom.minidom.parse(xml_file)
    collection = DOMTree.documentElement
    nodes = collection.getElementsByTagName("node")
    for node in nodes:
        cur_json = {
            "name": "",
            "amenity": "",
            "location": {
                "type": "Point",
                "coordinates": [
                    float(node.getAttribute("lon")),
                    float(node.getAttribute("lat"))
                ]
            },
            "address": {
                "country": "",
                "city": "",
                "postcode": "",
                "street": "",
                "housenumber": "0"
            }
        }
        subnodes = node.getElementsByTagName("tag")
        for subnode in subnodes:
            k = subnode.getAttribute("k")
            v = subnode.getAttribute("v")
            if k == "name" or k == "amenity":
                cur_json[k] = v
            elif k[:5] == "addr:":
                cur_json["address"][k[5:]]= v
        data.append(cur_json)
    with io.open(json_file, 'w', encoding='utf-8') as f:
        f.write(unicode(json.dumps(data, ensure_ascii=False, indent=2)))
    if(verbose):
        print "Finished creating json file"
