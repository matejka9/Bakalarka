# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('mapa', '0001_initial'),
    ]

    operations = [
        migrations.CreateModel(
            name='Podium',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('nazov', models.CharField(default=None, max_length=255)),
                ('lavy_horny_roh_x', models.FloatField(default=None)),
                ('lavy_horny_roh_y', models.FloatField(default=None)),
                ('pravy_spodny_roh_x', models.FloatField(default=None)),
                ('pravy_spodny_roh_y', models.FloatField(default=None)),
                ('mapa_id', models.ForeignKey(default=None, to='mapa.Mapa')),
            ],
            options={
                'verbose_name_plural': 'Podia',
            },
        ),
        migrations.CreateModel(
            name='Skupina',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('nazov', models.CharField(default=None, max_length=255)),
                ('foto', models.ImageField(null=True, upload_to=b'')),
            ],
            options={
                'verbose_name_plural': 'Skupiny',
            },
        ),
        migrations.CreateModel(
            name='SkupinaPodia',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('detail', models.TextField(max_length=10000, null=True)),
                ('cas_od', models.DateTimeField(default=None)),
                ('cas_do', models.DateTimeField(default=None)),
                ('podium_id', models.ForeignKey(default=None, to='podium.Podium')),
                ('skupina_id', models.ForeignKey(default=None, to='podium.Skupina')),
            ],
            options={
                'verbose_name_plural': 'Skupiny podia',
            },
        ),
    ]
