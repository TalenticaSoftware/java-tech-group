#!/bin/sh

# Wait for the Redis servers to start
sleep 5

# Create the cluster
echo "yes" | redis-cli --cluster create \
  redis-cluster-node-1:6379 \
  redis-cluster-node-2:6379 \
  redis-cluster-node-3:6379 \
  --cluster-replicas 0

# Keep the container running
tail -f /dev/null
