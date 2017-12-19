# Promotes a snapshot to be released to bintray

# This script is a wrapper around a simple curl command that displays the
# JSON result of the HTTP request and returns a non-zero return code in case
# of an HTTP error. Both at once is not possible with curl alone.

# Parameters:
# 1 - name of the build to be promoted
# 2 - build number of the build to be promoted
# 3 - bintray user name
# 4 - bintray API key

http_code=$(curl -s -o out.json -w '%{http_code}' -X POST -d "" -u $3:$4 http://oss.jfrog.org/api/plugins/build/promote/snapshotsToBintray/$1/$2;)
if [ $http_code -ge 200 ] && [ $http_code -le 299 ]; then
	exit 0;
fi

echo "Error while promoting snapshot!"
cat out.json

exit 1
