# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('Podujatie', '0001_initial'),
    ]

    operations = [
        migrations.CreateModel(
            name='Mapa',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('mapicka', models.ImageField(default=None, upload_to=b'')),
                ('typ', models.CharField(max_length=1, choices=[(0, 'Hlavna'), (1, 'Vedlajsia')])),
                ('lavy_horny_roh_gps_latitude', models.FloatField(default=None)),
                ('lavy_horny_roh_gps_longitude', models.FloatField(default=None)),
                ('pravy_spodny_roh_gps_latitude', models.FloatField(default=None)),
                ('pravy_spodny_roh_gps_longitude', models.FloatField(default=None)),
                ('podujatie_id', models.ForeignKey(default=None, to='Podujatie.Podujatie')),
            ],
            options={
                'verbose_name_plural': 'Mapy',
            },
        ),
    ]
