import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CategoryService } from '../../categories/category.service';
import { ItemService } from '../item.service';

@Component({
    selector: 'app-item-add',
    standalone: true,
    imports: [CommonModule, RouterModule, ReactiveFormsModule],
    templateUrl: './item-add.component.html',
    styleUrl: './item-add.component.css'
})

export class ItemAddComponent implements OnInit {
    itemForm: FormGroup;
    loading = false;
    error: string | null = null;
    categoryId: string = '';
    categoryName: string = '';

    constructor(
        private fb: FormBuilder,
        private itemService: ItemService,
        private categoryService: CategoryService,
        private router:Router,
        private route: ActivatedRoute
    ) {
        this.itemForm = this.fb.group({
            name: ['', Validators.required],
            price: ['', Validators.required]
        });
    }

    ngOnInit(): void {
        this.categoryId = this.route.snapshot.paramMap.get('categoryId') || '';
        this.loadCategoryName();
    }

    loadFranchiseName(): void {
        this.categoryService.getCategory(this.categoryId).subscribe({
            next: (cat) => {
                this.categoryName = category.name;
            },
            error: (err) => {
                console.error(err);
            }
        });
    }

    onSubmit(): void {
        if (this.playerForm.valid) {
            this.loading = true;
            this.error = null;
            const formValue = this.playerForm.value;
            const playerData = {
                firstName: formValue.firstName,
                lastName: formValue.lastName,
                age: formValue.age,
                position: formValue.position
            };

            this.playerService.createPlayer(this.franchiseId, playerData).subscribe({
                next: () => {
                    this.router.navigate(['/franchises', this.franchiseId]);
                },
                error: (err) => {
                    this.error = 'Failed to create player';
                    this.loading = false;
                    console.error(err);
                }
            });
        }
    }
}
