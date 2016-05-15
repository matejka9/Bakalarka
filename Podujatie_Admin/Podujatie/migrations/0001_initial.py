# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import migrations, models
from django.conf import settings


class Migration(migrations.Migration):

    dependencies = [
        migrations.swappable_dependency(settings.AUTH_USER_MODEL),
    ]

    operations = [
        migrations.CreateModel(
            name='Podujatie',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('nazov', models.CharField(default=None, max_length=255)),
                ('typ', models.CharField(max_length=1, choices=[(0, 'Festival'), (1, 'Hody')])),
                ('datum_od', models.DateField(default=None)),
                ('datum_do', models.DateField(default=None)),
                ('administrator_id', models.ForeignKey(default=None, to=settings.AUTH_USER_MODEL)),
            ],
            options={
                'verbose_name_plural': 'Podujatia',
            },
        ),
    ]
