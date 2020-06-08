#!/bin/bash

echo -e 'cd ./SpringJMS ...'
cd ./SpringJMS

tar xvf ./apache-activemq-5.15.12-bin.tar.gz

./apache-activemq-5.15.12/bin/activemq start

# trap ctrl-c and call ctrl_c()
trap ctrl_c INT

function ctrl_c() {
        echo "** Trapped CTRL-C"
	gradle -stop
	./apache-activemq-5.15.12/bin/activemq stop
	echo "Current path: $PWD"
	rm -r ./apache-activemq-5.15.12
}

gradle clean build bootRun


: '
while true; do
    echo -n "Print end to close the application: " 
    read DONE

    if [ "$DONE" == "end" ]; then
       ctrl_c
       exit 1
    else
       echo "Print end to close the application:"
       read DONE
    fi
done
'

#read -p "Continue? (Y/N): " confirm && [[ $confirm == [yY] || $confirm == [yY][eE][sS] ]] || exit 1






