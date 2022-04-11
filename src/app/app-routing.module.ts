import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PageLoginComponent} from "./pages/page-login/page-login.component";
import {PageInscriptionComponent} from "./pages/page-inscription/page-inscription.component";
import {PageDashboardComponent} from "./pages/page-dashboard/page-dashboard.component";
import {PageStatistiquesComponent} from "./pages/page-statistiques/page-statistiques.component";
import {PageArticleComponent} from "./pages/articles/page-article/page-article.component";
import {NouvelArticleComponent} from "./pages/articles/nouvel-article/nouvel-article.component";
import {PageMvtstockComponent} from "./pages/mvtstock/page-mvtstock/page-mvtstock.component";
import {PageClientComponent} from "./pages/clients/page-client/page-client.component";
import {PageFournisseurComponent} from "./pages/fournisseurs/page-fournisseur/page-fournisseur.component";
import {NouveauClientComponent} from "./pages/clients/nouveau-client/nouveau-client.component";
import {NouveauFournisseursComponent} from "./pages/fournisseurs/nouveau-fournisseurs/nouveau-fournisseurs.component";
import {PageCmdCltComponent} from "./pages/page-cmd-clt/page-cmd-clt.component";
import {PageCmdFrnsComponent} from "./pages/page-cmd-frns/page-cmd-frns.component";

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
      },
      {
        path:'nouveauclient',
        component:NouveauClientComponent
      },
      {
        path:'nouveaufournisseur',
        component:NouveauFournisseursComponent
      },
      {
        path:'commandeclient',
        component:PageCmdCltComponent
      },
      {
        path:'commandefournisseur',
        component:PageCmdFrnsComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
