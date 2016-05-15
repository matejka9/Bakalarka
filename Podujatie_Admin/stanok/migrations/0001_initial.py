# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('mapa', '0001_initial'),
    ]

    operations = [
        migrations.CreateModel(
            name='Stanok',
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
                'verbose_name_plural': 'Stanky',
            },
        ),
        migrations.CreateModel(
            name='Tovar',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('nazov', models.CharField(default=None, max_length=255)),
                ('foto', models.ImageField(null=True, upload_to=b'')),
            ],
            options={
                'verbose_name_plural': 'Tovary',
            },
        ),
        migrations.CreateModel(
            name='TovarStanku',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('detail', models.TextField(max_length=10000, null=True)),
                ('cena', models.FloatField(default=None)),
                ('stanok_id', models.ForeignKey(default=None, to='stanok.Stanok')),
                ('tovar_id', models.ForeignKey(default=None, to='stanok.Tovar')),
            ],
            options={
                'verbose_name_plural': 'Tovar Stankov',
            },
        ),
    ]
