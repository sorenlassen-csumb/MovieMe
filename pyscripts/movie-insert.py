import sys
from py2neo import Graph, Node, Relationship
import json
import requests

'''
    Run this script with the argument -d to delete all
    nodes and edges from database.
'''

graph = Graph("http://neo4j:MovieMe@localhost:7474/db/data")

def insertNode(data):
    moviePropertiesDict = dict()

    try:
        moviePropertiesDict["TITLE"] = data["Title"].encode("utf-8")
        moviePropertiesDict["PLOT"] = data["Plot"].encode("utf-8")
        moviePropertiesDict["RATED"] = data["Rated"].encode("utf-8")
        moviePropertiesDict["RATING"] = data["imdbRating"].encode("utf-8")
        moviePropertiesDict["WRITER"] = data["Writer"].encode("utf-8")
        moviePropertiesDict["DIRECTOR"] = data["Director"].encode("utf-8")
        moviePropertiesDict["RELEASED"] = data["Released"].encode("utf-8")
        moviePropertiesDict["ACTORS"] = data["Actors"].encode("utf-8")
        moviePropertiesDict["GENRE"] = data["Genre"].encode("utf-8")
        moviePropertiesDict["RUNTIME"] = data["Runtime"].encode("utf-8")
        moviePropertiesDict["POSTER"] = data["Poster"].encode("utf-8")
        moviePropertiesDict["IMDBID"] = data["imdbID"].encode("utf-8")
    except:
        return

    movieNode = Node.cast("MOVIE", moviePropertiesDict)

    graph.create(movieNode)

def movieRequest(baseURL, idList):
    for movieId in idList:
        parameters = {"i" : movieId}
        try:
            response = requests.get(baseURL, params=parameters)
            data = response.json()
        except:
            continue

        # print data

        insertNode(data)

    # parameters = {"i" : idList[0]}
    #
    # response = requests.get(baseURL, params=parameters)
    #
    # data = response.json()

    # print data

def getIdList():
    idFile = open("idFile.txt", "r")

    lines = idFile.readlines()

    idList = list()

    for line in lines:
        line = line.rstrip("\n")
        idList.append(line)

    return idList

def main():
    graph.delete_all()

    baseURL = "http://www.omdbapi.com/"

    idList = getIdList()

    movieRequest(baseURL, idList)

main()
