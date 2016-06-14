# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('mapa', '__first__'),
    ]

    operations = [
        migrations.CreateModel(
            name='Notifikacia',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('nadpis', models.CharField(default=None, max_length=255)),
                ('text', models.CharField(default=None, max_length=255)),
                ('mapa', models.ForeignKey(default=None, to='mapa.Mapa')),
            ],
            options={
                'verbose_name_plural': 'Notifikacie',
            },
        ),
    ]
