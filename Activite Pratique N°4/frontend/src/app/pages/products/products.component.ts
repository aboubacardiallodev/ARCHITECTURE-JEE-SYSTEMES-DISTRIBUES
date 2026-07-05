import { Component, OnInit, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/product.model';
import { ConfirmModalComponent } from '../../components/confirm-modal/confirm-modal.component';

@Component({
  selector: 'app-products',
  imports: [CommonModule, ConfirmModalComponent],
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent implements OnInit {
  @ViewChild(ConfirmModalComponent) confirmModal!: ConfirmModalComponent;
  
  products: Product[] = [];
  productToDelete: Product | null = null;

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(): void {
    this.productService.getProducts().subscribe(products => {
      this.products = products;
    });
  }

  toggleSelected(product: Product): void {
    const newValue = !product.selected;
    this.productService.updateSelected(product.id, newValue).subscribe(updated => {
      product.selected = updated.selected;
    });
  }

  onDelete(product: Product): void {
    this.productToDelete = product;
    this.confirmModal.open(
      'Confirmer la suppression',
      `Êtes-vous sûr de vouloir supprimer "${product.name}" ? Cette action est irréversible.`
    );
  }

  onConfirmDelete(): void {
    if (this.productToDelete) {
      this.productService.deleteProduct(this.productToDelete.id).subscribe(() => {
        this.products = this.products.filter(p => p.id !== this.productToDelete!.id);
        this.productToDelete = null;
      });
    }
  }

  onCancelDelete(): void {
    this.productToDelete = null;
  }
}
