import {Component, OnInit} from '@angular/core';
import {Menu} from "./Menu";
import {Router} from "@angular/router";
@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {
  public MenuPropreties: Array<Menu> = [
    {
      id: '1',
      title: 'Tableau de bord',
      icon: 'fas fa-chart-line',
      url: '',
      sousMenu: [
        {
          id: '11',
          title: 'Vue d\'ensemble',
          icon: 'fas fa-chart-pie',
          url: '',

        },
        {
          id: '12',
          title: 'Statistiques',
          icon: 'fas fa-chart-bar',
          url: 'statistiques',

        }
      ]
    },
    {
      id: '2',
      title: 'Articles',
      icon: 'fas fa-boxes',
      url: '',
      sousMenu:[
        {
          id: '21',
          title: 'Article',
          icon: 'fas fa-box',
          url: 'articles',
        },
        {
          id: '22',
          title: 'Mouvements de stock',
          icon: 'fa-brands fa-stack-overflow',
          url: 'mvtstock',
        }
      ]
    },
    {
      id: '3',
      title: 'Clients',
      icon: 'fas fa-users',
      url: '',
      sousMenu:[
        {
          id: '31',
          title: 'Clients',
          icon: 'fas fa-users',
          url: 'client',
        },
        {
          id: '32',
          title: 'Commande Clients',
          icon: 'fas fa-basket-shopping',
          url: '',
        }
      ]
    },
    {
      id: '4',
      title: 'Fournisseurs',
      icon: 'fas fa-users',
      url: '',
      sousMenu:[
        {
          id: '41',
          title: 'Fournisseurs',
          icon: 'fas fa-users',
          url: '',
        },
        {
          id: '42',
          title: 'Commande Fournisseurs',
          icon: 'fas fa-truck',
          url: '',
        }
      ]
    },
    {
      id: '5',
      title: 'Parametrages',
      icon: 'fas fa-cogs',
      url: '',
      sousMenu:[
        {
          id: '51',
          title: 'Categories',
          icon: 'fas fa-tools',
          url: '',
        },
        {
          id: '52',
          title: 'Utilisateurs',
          icon: 'fas fa-users-cog',
          url: '',
        }
      ]
    }
  ]


  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  navigate(url?:string) {
    this.router.navigate([url]);
  }
}
