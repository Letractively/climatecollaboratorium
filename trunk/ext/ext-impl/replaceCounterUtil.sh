#!/bin/bash
for plik in `grep CounterUtil.increment . -r  --exclude="*svn*" -l --include="*java"`
do
    cat $plik | sed s/com\.liferay\.counter.service\.persistence\.CounterUtil/com\.liferay\.counter\.service\.CounterLocalServiceUtil/g | sed s/CounterUtil/CounterLocalServiceUtil/g > ../../../tmp_src
    cp ../../../tmp_src $plik
done


