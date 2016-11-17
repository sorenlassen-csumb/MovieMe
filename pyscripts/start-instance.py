import boto3
from botocore.exceptions import ClientError
import sys

client = boto3.client('ec2')

def get_public_dns(instance):
    return instance['Reservations'][0]['Instances'][0]['PublicDnsName']

def start(instance_id):
    try:
        instance = client.start_instances(
            InstanceIds=[
                instance_id,
            ]
        )
    except ClientError as e:
        print e
            

if __name__ == "__main__":
    if(len(sys.argv) > 1):
        start(sys.argv[1])
    else: 
        print "Incorrect amount of arguments received."
