import sys
from py2neo import Graph, Node, Relationship

'''
    Run this script with the argument -d to delete all
    nodes and edges from database.
'''

graph = Graph("http://neo4j:MovieMe@localhost:7474/db/data")

'''
    A uniqueness constraint is set on the EMAIL property of the User entity
    along with a uniqueness constraint on the TITLE property of the Movie entity.
'''

try:
    graph.schema.create_uniqueness_constraint("User", "EMAIL")
    graph.schema.create_uniqueness_constraint("Movie", "TITLE")
except:
    pass

userList = [
    {"NAME" : "Hugo Argueta", "AGE" : "22", "EMAIL" : "hugoargueta94@gmail.com"},
    {"NAME" : "Samuel Villavicencio", "AGE" : "21", "EMAIL" : "samuelrey010@gmail.com"}
]

movieList = [{"TITLE" : "Django Unchained", "RATING" : "8.5", "RELEASE_DATE" : "25 December 2012"},
             {"TITLE" : "Friday", "RATING" : "7.3", "RELEASE_DATE" : "26 April 1995"},
             {"TITLE" : "Interstellar", "RATING" : "8.6", "RELEASE_DATE" : "7 November 2014"}
            ]

movieLikes = {"hugoargueta94@gmail.com" : [{"TITLE" : "Django Unchained", "RATING" : "8.5", "RELEASE_DATE" : "25 December 2012"},
                                           {"TITLE" : "Friday", "RATING" : "7.3", "RELEASE_DATE" : "26 April 1995"},
                                           {"TITLE" : "Interstellar", "RATING" : "8.6", "RELEASE_DATE" : "7 November 2014"}
                                          ]
            }

def clearDB():
    graph.delete_all()

def createUser():
    pass

def createMovies():
    for movie in movieList:
        movieNode = Node.cast("Movie", movie)

        try:
            graph.create(movieNode)
        except:
            print "Movie " + movie["TITLE"] + " already exists."

def createUsers():
    for user in userList:
        userNode = Node.cast("User", user)

        try:
            graph.create(userNode)
        except:
            print "User " + user["EMAIL"] + " already exists."

def createLikes():
    for (user, movies) in movieLikes.iteritems():
        userNode = graph.find_one("User", property_key="EMAIL", property_value=user)
        for movie in movies:
            movieNode = graph.find_one("Movie", property_key="TITLE", property_value=movie["TITLE"])
            graph.create(Relationship(userNode, "LIKES", movieNode))

def main():
    if "-d" in sys.argv:
        clearDB()

    createUsers()
    createMovies()
    createLikes()

main()
