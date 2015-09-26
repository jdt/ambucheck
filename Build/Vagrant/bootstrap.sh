#!/usr/bin/env bash

echo 'Europe/Brussels' > /etc/timezone
dpkg-reconfigure --frontend noninteractive tzdata

wget https://www.dotdeb.org/dotdeb.gpg
apt-key add dotdeb.gpg

cp /vagrant/Build/Vagrant/apt_sources.list /etc/apt/sources.list

apt-get update

apt-get install -y apache2
if ! [ -L /var/www ]; then
  rm -rf /var/www
  ln -fs /vagrant/Source/Web /var/www
fi

apt-get install -y php5=5.6.*
apt-get install -y libapache2-mod-php5=5.6.*

cp /vagrant/Build/Vagrant/php5_php.ini /etc/php5/apache2/php.ini
cp /vagrant/Build/Vagrant/php5_php.ini /etc/php5/cli/php.ini

cp /vagrant/Build/Vagrant/apache2_php5.conf /etc/apache2/mods-enabled/php5.conf
cp /vagrant/Build/Vagrant/apache2_php5.load /etc/apache2/mods-enabled/php5.load

/etc/init.d/apache2 restart