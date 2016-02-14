from django.contrib import admin

from models import Tovar
# Register your models here.

class TovarAdmin(admin.ModelAdmin):
    model = Tovar
    list_display = ['nazov']
    search_fields = ['nazov']
    
    
admin.site.register(Tovar, TovarAdmin)