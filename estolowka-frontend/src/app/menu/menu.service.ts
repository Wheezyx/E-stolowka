import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {environment} from "../../environments/environment";
import {WeeklyMenuItem} from './model/weekly-menu-item';
import {Menu} from './model/menu';
import {PriceList} from "./model/price-list";

@Injectable()
export class MenuService {

  constructor(private _http: HttpClient) {
  }

  updateMenuPrices(priceList: PriceList) {
    return this._http.post(environment.menuUrl + '/prices', priceList);
  }

  getMenuPrices(): Observable<PriceList> {
    return this._http.get<PriceList>(environment.menuUrl + '/prices');
  }

  sendMenu(menu: WeeklyMenuItem[]): Observable<WeeklyMenuItem[]> {
    return this._http.post<WeeklyMenuItem[]>(environment.menuUrl, this.createMenu(menu));
  }

  getMenu(): Observable<WeeklyMenuItem[]> {
    return this._http.get<WeeklyMenuItem[]>(environment.menuUrl);
  }

  private createMenu(meals: WeeklyMenuItem[]): Menu {
    var menu = new Menu();
    menu.meals = meals;
    return menu;
  }
}
