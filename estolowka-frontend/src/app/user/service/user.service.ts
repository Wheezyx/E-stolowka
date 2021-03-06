import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";
import {User} from "../model/user";
import {Page} from '../../util/page';
import {Pageable} from '../../util/pageable';

@Injectable()
export class UserService {

  constructor(private _http: HttpClient) {
  }

  changeUserStatus(email: string): Observable<Object> {
    return this._http.post<User>(environment.usersUrl + '/status', email);
  }

  getUsers(pageable: Pageable): Observable<Page<User>> {
    const params = new HttpParams()
      .append('page', pageable.page.toString())
      .append('size', pageable.size.toString())
      .append('sort', pageable.sort + ',' + pageable.direction);
    return this._http.get<Page<User>>(environment.usersUrl, {params: params});
  }

  changeUserPassword(email: string, oldPassword: string, newPassword: string): Observable<Object> {
    return this._http.post(environment.usersUrl + '/change/password',
      {email: email, oldPassword: oldPassword, newPassword: newPassword});
  }
}
