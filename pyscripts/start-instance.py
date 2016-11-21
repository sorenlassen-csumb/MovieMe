import boto3
from botocore.exceptions import ClientError
import sys

client = boto3.client("ec2")

def get_public_dns(instance):
    return instance["Reservations"][0]["Instances"][0]["PublicDnsName"]

def start_instance(instance_id):
    try:
        instance = client.start_instances(
            InstanceIds=[
                instance_id,
            ]
        )
        # TODO: SSH tunnel to instance and start neo4j service [11/20/16, Clarissa Vazquez]
    except ClientError as e:
        print e

def stop_instance(instance_id):
    try:
        instance = client.stop_instances(
            InstanceIds=[
                instance_id,
            ]
        )
        #TODO: SSH tunnel to instance and stop neo4j service [11/20/16, Clarissa Vazquez]
    except ClientError as e:
        print e
            

if __name__ == "__main__":
    # First argument should be either "start" or "stop".
    # Second argument should be a valid EC2 instance id.
    if len(sys.argv) == 3:
        if sys.argv[1] == "start":
            start_instance(sys.argv[2])
        elif sys.argv[1] == "stop":
            stop_instance(sys.argv[2])
        else:
            print "Invalid argument."
    else: 
        print "Incorrect arguments."
