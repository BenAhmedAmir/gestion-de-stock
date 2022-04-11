import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {PageLoginComponent} from "./pages/page-login/page-login.component";
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {PageInscriptionComponent} from './pages/page-inscription/page-inscription.component';
import {PageDashboardComponent} from './pages/page-dashboard/page-dashboard.component';
import {PageStatistiquesComponent} from './pages/page-statistiques/page-statistiques.component';
import {MenuComponent} from "./composants/menu/menu.component";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HeaderComponent} from './composants/header/header.component';
import {PageArticleComponent} from './pages/articles/page-article/page-article.component';
import {DetailArticleComponent} from './composants/detail-article/detail-article.component';
import {PaginationComponent} from './composants/pagination/pagination.component';
import {BoutonActionComponent} from './composants/bouton-action/bouton-action.component';
import {NouvelArticleComponent} from './pages/articles/nouvel-article/nouvel-article.component';
import {PageMvtstockComponent} from './pages/mvtstock/page-mvtstock/page-mvtstock.component';
import {DetailMvtStockArticleComponent,}
  from './composants/detail-mvt-stock-article/detail-mvt-stock-article.component';
import {DetailMvtStockComponent} from "./composants/detail-mvt-stock/detail-mvt-stock.component";
import { DetailCltFrnsComponent } from './composants/detail-clt-frns/detail-clt-frns.component';
import { PageClientComponent } from './pages/clients/page-client/page-client.component';
import { PageFournisseurComponent } from './pages/fournisseurs/page-fournisseur/page-fournisseur.component';
import { NouveauCltFrnsComponent } from './composants/nouveau-clt-frns/nouveau-clt-frns.component';
import { NouveauClientComponent } from './pages/clients/nouveau-client/nouveau-client.component';
import { NouveauFournisseursComponent } from './pages/fournisseurs/nouveau-fournisseurs/nouveau-fournisseurs.component';
import { DetailCmdCltFrnsComponent } from './composants/detail-cmd-clt-frns/detail-cmd-clt-frns.component';
import { PageCmdCltComponent } from './pages/page-cmd-clt/page-cmd-clt.component';
import { PageCmdFrnsComponent } from './pages/page-cmd-frns/page-cmd-frns.component';
import { DetailCmdComponent } from './composants/detail-cmd/detail-cmd.component';

@NgModule({
  declarations: [
    AppComponent,
    PageLoginComponent,
    PageInscriptionComponent,
    PageDashboardComponent,
    PageStatistiquesComponent,
    MenuComponent,
    HeaderComponent,
    PageArticleComponent,
    DetailArticleComponent,
    PaginationComponent,
    BoutonActionComponent,
    NouvelArticleComponent,
    PageMvtstockComponent,
    DetailMvtStockArticleComponent,
    DetailMvtStockComponent,
    DetailCltFrnsComponent,
    PageClientComponent,
    PageFournisseurComponent,
    NouveauCltFrnsComponent,
    NouveauClientComponent,
    NouveauFournisseursComponent,
    DetailCmdCltFrnsComponent,
    PageCmdCltComponent,
    PageCmdFrnsComponent,
    DetailCmdComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FontAwesomeModule,
    BrowserAnimationsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
