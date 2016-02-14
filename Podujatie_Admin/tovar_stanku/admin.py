from django.contrib import admin

from models import TovarStanku
# Register your models here.

class TovarStankuAdmin(admin.ModelAdmin):
    model = TovarStanku
    list_display = ['stanok_id', 'tovar_id', 'detail', 'cena']
    list_filter = ['stanok_id', 'tovar_id']
    search_fields = ['nazov']
    
    
admin.site.register(TovarStanku, TovarStankuAdmin)