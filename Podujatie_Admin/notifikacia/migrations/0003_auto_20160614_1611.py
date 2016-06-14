# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('notifikacia', '0002_notifikacia_detail'),
    ]

    operations = [
        migrations.AlterField(
            model_name='notifikacia',
            name='detail',
            field=models.TextField(default=None, max_length=10000),
        ),
    ]
