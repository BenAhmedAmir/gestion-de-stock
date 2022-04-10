import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PageLoginComponent} from "./pages/page-login/page-login.component";
import {PageInscriptionComponent} from "./pages/page-inscription/page-inscription.component";
import {PageDashboardComponent} from "./pages/page-dashboard/page-dashboard.component";
import {PageStatistiquesComponent} from "./pages/page-statistiques/page-statistiques.component";
import {PageArticleComponent} from "./pages/articles/page-article/page-article.component";
import {NouvelArticleComponent} from "./pages/articles/nouvel-article/nouvel-article.component";
import {PageMvtstockComponent} from "./pages/mvtstock/page-mvtstock/page-mvtstock.component";
import {PageClientComponent} from "./pages/page-client/page-client.component";
import {PageFournisseurComponent} from "./pages/page-fournisseur/page-fournisseur.component";

const routes: Routes = [
  {
    path:'login',component: PageLoginComponent
  },
  {
    path:'inscrire', component:PageInscriptionComponent
  },
  {
    path:'', component:PageDashboardComponent,
    children: [
      {
        path:'statistiques',
        component:PageStatistiquesComponent
      },
      {
        path:'articles',
        component:PageArticleComponent
      },
      {
        path:'nouvelarticle',
        component:NouvelArticleComponent
      },
      {
        path:'mvtstock',
        component:PageMvtstockComponent
      },
      {
        path:'client',
        component:PageClientComponent
      },
      {
        path:'fournisseur',
        component:PageFournisseurComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
