# Add script to run program here.
# Complete bin/setup so that after it is
# run, bin/parking_lot can be used to launch
# it.
cd ..
if [ $# -eq 1 ]
then
    file="$1"
    java -cp target/parking-system-0.0.1-SNAPSHOT.jar org.gojek.parkingsystem.ParkingSystemApplication "$file"
else
    java -cp target/parking-system-0.0.1-SNAPSHOT.jar org.gojek.parkingsystem.ParkingSystemApplication
fi