#!/usr/bin/python
import MySQLdb as mysql
import os

db = mysql.connect(host="localhost",
					 port=3306,
                     user="root", 
                     passwd="", 
                     db="facial")

# you must create a Cursor object. It will let
#  you execute all the queries you need
cur = db.cursor() 

# Use all the SQL you like
os.chdir('FaceSets/Names')
for filename in os.listdir('.'):
	race = filename[:-4]
	print "\nFilename: " + str(filename)
	print "Race: " + str(race)
	print "-----------------------"
	with open(filename, "r") as names:
		for name in names:
        	# UPDATE 
			query = "UPDATE stars SET " + str(race) + "=" + str(1) + " WHERE name=\'" + name.rstrip() +"\'"
			print query
			cur.execute(query)

		# OUTPUT && SANITY CHECK
		cur.execute("SELECT * FROM stars LIMIT 10")
		rows = cur.fetchall()
		print ""
		for row in rows:
			#			 Latina	  White	  Asian	  Black 
			print row[0], row[5], row[6], row[7], row[8]

db.commit()
print "\nDone\n"