# -*- coding: utf-8 -*-
# Generated by Django 1.9.2 on 2016-02-14 14:46
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('podujatie', '0001_initial'),
    ]

    operations = [
        migrations.AlterModelOptions(
            name='podujatie',
            options={'verbose_name_plural': 'Podujatia'},
        ),
        migrations.AlterField(
            model_name='podujatie',
            name='nazov',
            field=models.CharField(default=None, max_length=255),
        ),
    ]