#!/usr/bin/python
from psycopg2 import connect
import os
import sys
import getopt
DIR = os.path.dirname(os.path.abspath(__file__))
class DBConnection:
    def __init__(self,host,user,password,port,dbname):
        self.host = host
        self.user = user
        self.password = password
        self.port = port
        self.dbname = dbname
    def __startConnection(self):
        try:
            self.connection = connect(host=self.host,user=self.user,password=self.password,port=self.port,dbname=self.dbname)
            self.cursor = self.connection.cursor()
        except Exception as e:
            print('\033[91mError to connect. \n' + str(e) + '\033[0m')
        else:
            print('Connected!')

    def __closeConnection(self):
        try:
            self.cursor.close()
            self.connection.close()
        except Exception as e:
            print('\033[91mError to disconnect. \n' + str(e) + '\033[0m')
        else:
            print('Disconected')

    def querySet(self,sql):
        try:
            self.__startConnection()
            self.cursor.execute(sql)
        except Exception as e:
            self.connection.rollback()
            print('\033[91mError to execute query. \n' + str(e) + '\033[0m')
        else:
            self.connection.commit()
            print(self.cursor.statusmessage)
        finally:
            self.__closeConnection()

    def test(self):
        self.__startConnection()
        self.__closeConnection()



def main(argv):
    host = 'localhost'
    port = '5432'
    user = 'postgres'
    dbname = 'bamboo'
    password = ''
    test = ''
    try:
        opts, args = getopt.getopt(argv,"hH:p:u:d:t:",["host=","port=","user=","dbname=","test="])
    except getopt.GetoptError:
        print ('Try with  startDB.py -h')
        sys.exit(2)
    for opt, arg in opts:
        if opt == '-h':
            print('Connection options:')
            print('\t-H, --host=HOSTNAME      database server host or socket directory (default: {})'.format(host))
            print('\t-p, --port=PORT          database server port (default: {})'.format(port))
            print('\t-u, --user=USERNAME      database user name (default: {})'.format(user))
            print('\t-d, --dbname=DBNAME      database name to connect to (default: {})'.format(dbname))
            print('\t-t, --test=TESTFILE      database test file')
            sys.exit()
        elif opt in ("-H", "--host"):
            host = arg
        elif opt in ("-p", "--port"):
            port = arg
        elif opt in ("-u", "--user"):
            user = arg
        elif opt in ("-d", "--dbname"):
            dbname = arg
        elif opt in ("-t", "--test"):
            test = arg

    password = input('password for {} user:  '.format(user))
    dbc = DBConnection(host,user,password,port,dbname)
    fileList = os.listdir(DIR)
    fileList.sort()
    files = []
    print('These files will be executed.')
    for tmp in fileList:
        try:
            int(tmp[0])
            print('\t' + tmp)
            files.append(DIR+'/'+tmp)
        except Exception as e:
            pass
    if test != '':
        print('\t' + test)
        files.append(DIR+'/'+test)
    opc = input('are you agree? [y/n] >> ')
    if opc=='Y' or opc=='y':
        for tmp in files:
            file = open(tmp)
            sql = file.read()
            print('='*75)
            print('Execute :' + tmp)
            dbc.querySet(sql)
            file.close()
    else:
        print('It has not been executed.')

    input('Enter to exit.')



if __name__ == "__main__":
   main(sys.argv[1:])
