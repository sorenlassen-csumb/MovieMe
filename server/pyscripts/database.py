import py2neo
from py2neo.cypher.lang import cypher_escape

class Database:
    def __init__(self):
        self.graph = py2neo.Graph('http://neo4j:movieme@localhost:7474/db/data')
        self.labels_properties = {
                'User':['NAME','EMAIL'],
                'Movie':['TITLE','RELEASE','DIRECTOR','GENRE']
                }

    def search(self, text, field, label):
        try:
            if field in self.labels_properties[label]:
                statement = "MATCH (n:%s) WHERE n.%s =~ '(?i).*%s.*' RETURN n" % (cypher_escape(label), cypher_escape(field), cypher_escape(text))
                result = self.graph.cypher.execute(statement)
                return [list(record) for record in list(result)]
            else:
                print 'invalid property key: % (field)'
        except KeyError:
            print 'invalid label: %s' % (label)


if __name__ == '__main__':
    db = Database()
    result = db.search('hugo', 'NAME', 'User')
    print result
